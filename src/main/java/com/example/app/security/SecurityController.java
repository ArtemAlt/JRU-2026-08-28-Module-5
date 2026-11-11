package com.example.app.security;

import com.example.app.security.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SecurityController { //todo запустить что б работало + реализовать получение jwt + проверить как работает /admin через jwt + ** реализовать авторефреш
    private final AppUserService service;
    private final AuthenticationManager authenticationManager;
    private final HttpSessionSecurityContextRepository contextRep;
    public SecurityController(AppUserService service, AuthenticationManager authenticationManager) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.contextRep = new HttpSessionSecurityContextRepository();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        service.register(request.username(), request.password(), request.roles());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    /** Использовал тот же RegisterRequest для логина, роли в аутентификации не фигурируют*/
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        try {

        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.username(),request.password())
        );
            SecurityContext currentContrext = SecurityContextHolder.createEmptyContext();
            currentContrext.setAuthentication(authentication);
            contextRep.saveContext(currentContrext, httpRequest, httpResponse);
            return  ResponseEntity.ok("Succes");
        } catch (Exception e) {
            return ResponseEntity.ok("NO ENTRY!");
        }
    }
    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(Authentication authentication) {
            if (authentication == null) {
                return ResponseEntity.ok("Not authenticated user");
            }
        return ResponseEntity.ok("Name: " + authentication.getName()
                + ", Roles: " + authentication.getAuthorities());
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getCurrentUserAdmin(Authentication authentication) {
        return ResponseEntity.ok("Name: " + authentication.getName()
                + ", Roles: " + authentication.getAuthorities());
    }
}
