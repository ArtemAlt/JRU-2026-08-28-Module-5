package com.example.app.saga.event;

public record OrderConfirmedEvent(String eventId, String correlationId, Long orderId) {}