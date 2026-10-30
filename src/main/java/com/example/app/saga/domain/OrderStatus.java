package com.example.app.saga.domain;

public enum OrderStatus {
    PENDING,
    PAYMENT_PROCESSING,
    STOCK_PROCESSING,
    CONFIRMED,
    REJECTED,
    CANCELLED
}
