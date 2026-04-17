package com.ali.store.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProduct {
    private String name;
    private BigDecimal price;
    private String description;
    private Byte categoryId;
}
