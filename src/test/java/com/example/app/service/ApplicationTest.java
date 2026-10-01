package com.example.app.service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApplicationTest {
    @Autowired
    private UserService userService;
//    @Autowired
//    private UserRepository repository;
    @MockBean
    private UserRepository repository;

    @Test
    void checkUserServiceExists() {
        assertThat(userService).isNotNull();
    }

    @Test
    void shouldSaveAndReturnUser() {
        Long expectedId = 1L;
        User expectedUser = new User(expectedId, "Jonathan", "jonathan@gmail.com", 20);
//        repository.save(expectedUser);
        when(repository.findById(expectedId)).thenReturn(Optional.of(expectedUser));

        User userById = userService.getUserById(expectedId);
        assertThat(userById).isEqualTo(expectedUser);
    }
}