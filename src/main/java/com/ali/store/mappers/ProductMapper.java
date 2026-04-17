// java
package com.ali.store.mappers;

import com.ali.store.dtos.ProductDto;
import com.ali.store.dtos.*;
import com.ali.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDto ProductDto);
    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    void update(ProductDto ProductDto, @MappingTarget Product product);
}
