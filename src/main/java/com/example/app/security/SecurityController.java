package com.example.app.security;

import com.example.app.security.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SecurityController { //todo запустить что б работало + реализовать получение jwt + проверить как работает /admin через jwt + ** реализовать авторефреш
    private final AppUserService service;

    public SecurityController(AppUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        service.register(request.username(), request.password(), request.roles());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok("Name: " + authentication.getName()
                + ", Roles: " + authentication.getAuthorities());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getCurrentUserAdmin(Authentication authentication) {
        return ResponseEntity.ok("Name: " + authentication.getName()
                + ", Roles: " + authentication.getAuthorities());
    }
}
