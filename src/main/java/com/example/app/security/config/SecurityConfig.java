package com.example.app.security.config;

import com.example.app.security.JwtAuthenticationFilter;
import com.example.app.security.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * Конфигурирует основную цепочку фильтров безопасности (Security Filter Chain) для приложения.
     * Данная конфигурация адаптирована под Stateless REST API на базе JWT-токенов:

     *   Отключает защиту CSRF (передача токенов идет в заголовках, что делает атаки CSRF невозможными).
     *   Устанавливает сессионную политику в режим {@link SessionCreationPolicy#STATELESS} (сервер не хранит сессии в памяти).
     *   Открывает публичный доступ к эндпоинтам регистрации и аутентификации.
     *   Требует обязательную аутентификацию для всех остальных запросов в системе.
     *   Внедряет кастомный фильтр {@link JwtAuthenticationFilter} строго перед стандартным процессом аутентификации.
     * @param http объект конфигуратора безопасности {@link HttpSecurity}
     * @return собранный объект {@link SecurityFilterChain}, содержащий упорядоченный список фильтров
     * @throws Exception в случае ошибок конфигурации безопасности Spring Security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf(csrf -> csrf.disable())
               .sessionManagement(session
                       -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(
                       auth -> auth
                               .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                               .anyRequest().authenticated())
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
               .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
