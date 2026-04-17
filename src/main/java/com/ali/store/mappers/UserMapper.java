package com.ali.store.mappers;

import com.ali.store.dtos.RegisterUserRequest;
import com.ali.store.dtos.UpdateUserRequest;
import com.ali.store.dtos.UserDto;
import com.ali.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);

    void update(UpdateUserRequest request, @MappingTarget User user);
}