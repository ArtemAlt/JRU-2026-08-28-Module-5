package com.example.app.service;

import com.example.app.model.CreateUserDto;
import com.example.app.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User createUser(CreateUserDto userCreate);

    List<User> getAllUsers();
}
