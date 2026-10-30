package com.example.app.saga.event;

public record OrderRejectedEvent(String eventId, String correlationId, Long orderId, String reason) {}
