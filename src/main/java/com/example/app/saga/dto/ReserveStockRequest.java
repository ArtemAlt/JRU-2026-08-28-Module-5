package com.example.app.saga.dto;

import java.util.List;

public record ReserveStockRequest(Long orderId, List<OrderItem> items) {}