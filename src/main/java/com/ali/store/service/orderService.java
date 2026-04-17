package com.ali.store.service;


import com.ali.store.dtos.OrderDto;
import com.ali.store.exceptions.OrderNotFoundExecution;
import com.ali.store.mappers.OrderMapper;
import com.ali.store.repositories.OrderRepository;
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
