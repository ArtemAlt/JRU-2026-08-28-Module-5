package com.example.app.repository;

import com.example.app.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void getAll() throws Exception {
        User expectedUser = new User(1L, "Jonathan", "jonathan@gmail.com", 20);
        entityManager.persist(expectedUser);
        entityManager.flush();

        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
    }

}