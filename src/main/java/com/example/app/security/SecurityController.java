package com.example.app.security;

import com.example.app.security.model.AppRole;
import com.example.app.security.model.AppUser;
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

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class SecurityController { //todo запустить что б работало
    private final AppUserService service;
    private final JwtService jwtService; // добавлено поле сервиса

    // Добавлен jwtService в конструктор
    public SecurityController(AppUserService service, JwtService jwtService) {
        this.service = service;
        this.jwtService = jwtService;
    }

    // Изменен Void на String, будет возвращен статус и jwt токен
   // чтобы после регистрации сразу быть авторизованным
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        service.register(request.username(), request.password(), request.roles());
        String token = jwtService.generateToken(request.username(), request.roles());
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    // Добавлен метод для авторизации
    // на выходе статут и jwt token
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        AppUser user = service.verifyUser(request.username(), request.password());
        Set<String> roles = user.getRoles().stream()
                .map(AppRole::getName)
                .collect(Collectors.toSet());

        String token = jwtService.generateToken(user.getUsername(), roles);
        return ResponseEntity.ok(token);
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
