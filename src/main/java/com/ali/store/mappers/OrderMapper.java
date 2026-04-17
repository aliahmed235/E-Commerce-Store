package com.ali.store.mappers;

import com.ali.store.dtos.OrderDto;
import com.ali.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);

}
