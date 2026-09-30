package com.example.app.service;

import com.example.app.model.CreateUserDto;
import org.springframework.stereotype.Service;

@Service
public class CreateUserValidator {

    public void validateUser(CreateUserDto user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getAge() < 0) {
            throw new IllegalArgumentException("Age is required");
        }
        if (user.getAge() > 100) {
            throw new IllegalArgumentException("Age is greater than 100");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
    }
}
