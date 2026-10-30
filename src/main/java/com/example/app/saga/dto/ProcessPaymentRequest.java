package com.example.app.saga.dto;

import java.math.BigDecimal;

public record ProcessPaymentRequest(Long orderId, Long userId, BigDecimal amount) {}