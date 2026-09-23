package com.example.app.service;

import com.example.app.model.User;
import com.example.app.model.UserCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.app.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository repository;
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository repository) {
//        System.out.println("[UserService] UserService created with " + repository.getClass().getSimpleName());
        this.repository = repository;
    }

    public User createUser(User user) {
        return repository.save(user);
    }
    public User createUser(UserCreate userCreate) {
        User user = new User(null, userCreate.getName(), userCreate.getEmail(), userCreate.getAge());
        return repository.save(user);
    }

//    public User getUserById(Long id) {
//        log.info("getUserById with id: " + id);
//        User found = null;
//        try {
//            found = repository.findById(id)
//                    .orElseThrow(() -> new NoSuchElementException("User not found"));
//        } catch (NoSuchElementException e) {
//            log.info("User not found by id: " + id);
//        }
//        log.info("found user name: " + found.getName());
//        return found;
//    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User updateUser(Long id, User user) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("User not found");
        }
        user.setId(id);
        return repository.save(user);
    }

    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("User not found");
        }
        repository.deleteById(id);
    }
}
