// java
package com.ali.store.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    @JsonProperty("product_id")
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Byte categoryId;
}
