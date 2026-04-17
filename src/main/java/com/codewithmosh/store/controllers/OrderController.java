package com.codewithmosh.store.controllers;


import com.codewithmosh.store.dtos.ErrorDto;
import com.codewithmosh.store.dtos.OrderDto;
import com.codewithmosh.store.exceptions.OrderNotFoundExecution;
import com.codewithmosh.store.mappers.OrderMapper;
import com.codewithmosh.store.repositories.OrderRepository;
import com.codewithmosh.store.service.AuthService;
import com.codewithmosh.store.service.orderService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final orderService OrderService;


    @GetMapping
    public List<OrderDto> getAllOrders() {

       return OrderService.getAllOrders();

    }
    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable("orderId") Long orderId) {
        return OrderService.getOrderById(orderId);
    }

    @ExceptionHandler(OrderNotFoundExecution.class)
    public ResponseEntity<?> handleOrderNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorDto(ex.getMessage()));
    }
}
