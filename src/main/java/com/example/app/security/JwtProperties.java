package com.example.app.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Конфигурационное DTO для переноса настроек JWT в сервис JwtService
 * <p>
 * Исключает хардкод секретного ключа и времени жизни токена в исходном коде.
 * Параметры автоматически маппятся из файла конфигурации application.yml
 * по пути: app.security.jwt
 * </p>
 *
 * @param secret       секретная строка, используемая для генерации криптографической подписи токена
 * @param expirationMs время жизни сгенерированного токена в миллисекундах
 */
@ConfigurationProperties(prefix = "app.security.jwt")
public record JwtProperties (String secret, long expirationMs){}

