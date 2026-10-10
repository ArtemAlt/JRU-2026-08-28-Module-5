package com.example.app.controller;

import com.example.app.model.User;

public class Convertor {
    public ResponseUserDto toDto(User user) {
        ResponseUserDto dto = new ResponseUserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
//DDD