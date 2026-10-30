package com.example.app.saga.dto;

import java.math.BigDecimal;
import java.util.List;

public record SagaContext(
        Long userId,
        BigDecimal amount,
        List<OrderItem> items,
        String paymentId,
        String reservationId
) {
    public SagaContext withPaymentId(String pid) {
        return new SagaContext(userId, amount, items, pid, reservationId);
    }
    public SagaContext withReservationId(String rid) {
        return new SagaContext(userId, amount, items, paymentId, rid);
    }
}