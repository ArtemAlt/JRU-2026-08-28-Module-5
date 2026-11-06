package com.example.app.security.service;

import com.example.app.security.model.AppRole;
import com.example.app.security.model.AppUser;
import com.example.app.security.repositories.AppRoleRepository;
import com.example.app.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppUserService {
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository userRepository, AppRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser register(String username, String rawPassword, Set<String> roles) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username is already in use");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(rawPassword));

        Set<AppRole> rolesFounded = roles.stream().map(r -> roleRepository.findByName(r)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"))).collect(Collectors.toSet());
        appUser.setRoles(rolesFounded);
        return userRepository.save(appUser);
    }

    // Добавлен метод для верификации пользователя
    public AppUser verifyUser(String username, String rawPassword) {
        AppUser appUser = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("Invalid username or  password"));

        if(!passwordEncoder.matches(rawPassword, appUser.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return appUser;
    }

}
