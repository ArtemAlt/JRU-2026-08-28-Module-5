package com.example.app.saga.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(String eventId, String correlationId, Long orderId,
                                Long userId, BigDecimal totalAmount) {}
