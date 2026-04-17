// java
package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.*;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.entities.User;
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
