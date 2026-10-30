package com.example.app.saga.dto;

public record ProcessPaymentResponse(String paymentId, String status, String errorMessage) {}