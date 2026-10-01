package com.example.app.service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository repository;

    @Test
    void createUser() {
        Long expectedId = 1L;
        User expectedUser = new User(expectedId, "Jonathan", "jonathan@gmail.com", 20);
        when(repository.findById(expectedId)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.getUserById(expectedId);
        assertThat(actualUser.getName()).isEqualTo(expectedUser.getName());
    }
}