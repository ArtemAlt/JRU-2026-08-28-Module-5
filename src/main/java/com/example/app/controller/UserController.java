package com.example.app.controller;

import com.example.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.app.service.UserService;

import java.util.List;
import java.util.Scanner;

@Controller
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
//        System.out.println("[UserController] UserService created with " + service.getClass().getSimpleName());
        this.service = service;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== USER MANAGEMENT ===");
            System.out.println("1. Create user");
            System.out.println("2. List all users");
            System.out.println("3. Get user by ID");
            System.out.println("4. Update user");
            System.out.println("5. Delete user");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
//                case 1: -> createUser(scanner);
//                case 2 -> listUsers();
//                case 3 -> getUserById(scanner);
//                case 4 -> updateUser(scanner);
//                case 5 -> deleteUser(scanner);
//                case 6 -> {
//                    System.out.println("Goodbye!");
//                    return;
//                }
//                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void createUser(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();

        User user = new User(null, name, email, age);
        User created = service.createUser(user);
        System.out.println("User created: " + created);
    }

    private void listUsers() {
        List<User> users = service.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found");
        } else {
            users.forEach(System.out::println);
        }
    }

    private void getUserById(Scanner scanner) {
        System.out.print("User ID: ");
        Long id = scanner.nextLong();
        try {
            User user = service.getUserById(id);
            System.out.println("User found: " + user);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateUser(Scanner scanner) {
        System.out.print("User ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("New name: ");
        String name = scanner.nextLine();
        System.out.print("New email: ");
        String email = scanner.nextLine();
        System.out.print("New age: ");
        int age = scanner.nextInt();

        User user = new User(null, name, email, age);
        try {
            User updated = service.updateUser(id, user);
            System.out.println("User updated: " + updated);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteUser(Scanner scanner) {
        System.out.print("User ID: ");
        Long id = scanner.nextLong();
        try {
            service.deleteUser(id);
            System.out.println("User deleted");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
