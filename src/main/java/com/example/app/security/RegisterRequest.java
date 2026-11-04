package com.example.app.security;

import java.util.Set;

public record RegisterRequest(String username, String password, Set<String> roles) {
}
