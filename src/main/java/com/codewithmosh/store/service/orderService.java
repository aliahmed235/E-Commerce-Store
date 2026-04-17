package com.codewithmosh.store.service;


import com.codewithmosh.store.dtos.OrderDto;
import com.codewithmosh.store.exceptions.OrderNotFoundExecution;
import com.codewithmosh.store.mappers.OrderMapper;
import com.codewithmosh.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class orderService {
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var orders = orderRepository.getOrderByCustomers(user);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrderById(Long orderId) {

       var order = orderRepository.getOrderWithItems(orderId)
               .orElseThrow(OrderNotFoundExecution::new);

       var user = authService.getCurrentUser();
         if (!order.isPlacedBy(user)) {
              throw new AccessDeniedException("Access denied");
         }
         return orderMapper.toDto(order);
    }
}
