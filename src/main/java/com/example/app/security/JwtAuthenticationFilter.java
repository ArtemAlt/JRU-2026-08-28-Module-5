package com.example.app.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Фильтр аутентификации для обработки HTTP-заголовка Authorization.
 * <p>
 * Перехватывает каждый входящий запрос и проверяет наличие токена в формате "Bearer [token]".
 * Если токен валиден, извлекает из него пользователя и роли, помещая их в SecurityContextHolder.
 * Это позволяет Spring Security автоматически проверять права доступа к защищенным ресурсам
 * (таким как /me или /admin) на основе ролей пользователя.
 * </p>
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                Claims claims = jwtService.extractAllClaims(authHeader.substring(7));

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    List<?> roles = Optional.ofNullable(claims.get("roles", List.class)).orElse(List.of());

                    var authorities = roles.stream()
                            .map(r -> new SimpleGrantedAuthority(r.toString().startsWith("ROLE_") ? r.toString() : "ROLE_" + r))
                            .toList();

                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities)
                    );
                }
            } catch (Exception e) {
                logger.error("JWT Auth failed: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}

