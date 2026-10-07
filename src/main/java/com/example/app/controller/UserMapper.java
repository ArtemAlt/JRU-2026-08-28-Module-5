package com.example.app.controller;

import com.example.app.model.CreateUserDto;
import com.example.app.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    ResponseUserDto toDto(User user);

    User toEntity(CreateUserDto request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    void updateEntity(CreateUserDto request, @MappingTarget User user);
}