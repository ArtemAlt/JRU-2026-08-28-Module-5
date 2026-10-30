package com.example.app.saga.dto;

public record RefundRequest(Long orderId, String paymentId) {}