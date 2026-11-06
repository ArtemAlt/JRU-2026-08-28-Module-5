package com.example.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;

/**
 * Сервис для работы с JSON Web Tokens (JWT).
 * Отвечает за генерацию токенов при аутентификации и их валидацию/парсинг
 * при последующих запросах к защищенным ресурсам.
 * Данный сервис работает в режиме Stateless и извлекает роли пользователей
 * напрямую из тела токена (Claims), минимизируя обращения к базе данных.
 */
@Service
public class JwtService {

    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.secret().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Генерирует JWT токен для аутентифицированного пользователя.
     * В токен запекается имя пользователя (Subject) и список его ролей (Claim).
     *
     * @param username имя (логин) пользователя
     * @param roles    список ролей, назначенных пользователю (например, USER, ADMIN)
     * @return компактная строка JWT, подписанная секретным ключом приложения
     */
    public String generateToken(String username, Collection<String> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.expirationMs()))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Парсит JWT токен, проверяет его цифровую подпись, валидирует время жизни (Expiration)
     * и возвращает всю содержащуюся в нем полезную нагрузку (Claims).
     *
     * @param token строка JWT токена, полученная из заголовка Authorization
     * @return объект {@link Claims}, содержащий все данные (subject, roles, exp, iat)
     * @throws io.jsonwebtoken.JwtException если токен подделан, поврежден или срок его действия истек
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

