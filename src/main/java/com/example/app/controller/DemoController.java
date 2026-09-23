package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.model.UserCreate;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {
    private final UserService service;
    private final UserRepository repository;

    public DemoController(UserService service, UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!!!!!";
    }

    @GetMapping("/search") // http://localhost:8080/search?variable=World&number=10
    public String search(@RequestParam(value = "variable", defaultValue = "Zero") String name, @RequestParam Integer number) {
        if (name == null) {
            return "Hello World!!!!!";
        } else {
            return "Вы искали: " + name + " количество: " + number;
        }
    }

    public String getItems(
            @RequestParam String name,           // String
            @RequestParam int page,              // int
            @RequestParam long total,            // long
            @RequestParam double price,          // double
            @RequestParam boolean active,        // boolean
            @RequestParam Integer count) {       // Integer

        return String.format(
                "name=%s, page=%d, total=%d, price=%.2f, active=%b, count=%d",
                name, page, total, price, active, count
        );
    }

    @GetMapping("/filter") // http://localhost:8080/filter?category=book
    public Map<String, Object> filter(@RequestParam List<String> tags) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("tags", tags);
        response.put("count", tags.size());
        return response;
    }

    @GetMapping("/all") // http://localhost:8080/all?name=Bob&age=30&city=Moscow
    public Map<String, String> allParams(@RequestParam Map<String, String> params) {
        return params;
    }

    @GetMapping("user/{id}") // http://localhost:8080/user/1 (/2) (/3)
    public String getUser(@PathVariable("id") Long id) {
        return "User ID: " + id;
    }

    @GetMapping("/categories/{categoryId}/products/{productId}") // http://localhost:8080/categories/845464/products/852
    public Map<String, Object> getProduct(
            @PathVariable Long categoryId,
            @PathVariable Long productId) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("categoryId", categoryId);
        response.put("productId", productId);
        return response;
    }
    /*
    @GetMapping("/users/{id:[0-9]+}")           // Только цифры
    @GetMapping("/users/{id:[0-9]{4}}")         // Ровно 4 цифры
    @GetMapping("/users/{name:[a-zA-Z]+}")      // Только буквы
    @GetMapping("/codes/{code:[A-Z]{2}[0-9]{4}}") // AA1234
     */

    @PostMapping("/user")// curl -X POST "http://localhost:8080/user"
    // -H "Content-Type: application/json"
    // -d '[{"name":"Bob3", "email":"bob4.gogle.com", "age":30}, {"name":"Bob2", "email":"bob4.gogle.com", "age":30},
    // {"name":"Bob1", "email":"bob4.gogle.com", "age":30}]'

    public String createUser(@RequestBody UserCreate user) {
        User user1 = service.createUser(user);
        return "User created with ID: " + user1.getId();
    }
}
