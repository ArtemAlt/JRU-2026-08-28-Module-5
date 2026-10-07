package com.example.app.controller;

import com.example.app.model.User;

public class ResponseUserDto {
    private String name;
    private String email;
    private int age;

    public ResponseUserDto() {
    }

    public ResponseUserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
