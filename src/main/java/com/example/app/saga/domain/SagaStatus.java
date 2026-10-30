package com.example.app.saga.domain;

public enum SagaStatus {
    STARTED,
    PAYMENT_PENDING,
    STOCK_PENDING,
    COMPENSATING,
    COMPLETED,
    FAILED
}