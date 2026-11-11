package com.example.app.security.config;

import com.example.app.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .authorizeHttpRequests(
                auth ->
               auth
                       .requestMatchers("/api/v2/hello").permitAll()
                       .requestMatchers("/actuator/*").permitAll()
                       .requestMatchers("/categories/**").hasRole("ADMIN")
               .anyRequest().authenticated()
               )
               .formLogin(Customizer.withDefaults())
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
