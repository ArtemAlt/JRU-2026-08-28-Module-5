package com.example.app.saga.dto;

import java.math.BigDecimal;

public record OrderItem(Long productId, Integer quantity, BigDecimal price) {}