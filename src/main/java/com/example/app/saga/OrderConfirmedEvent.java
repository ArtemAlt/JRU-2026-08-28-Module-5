package com.example.app.saga;

public record OrderConfirmedEvent(String string, String correlationId, Long id) {
}
