package com.example.app.saga.dto;

import java.math.BigDecimal;
import java.util.List;

public class CreateOrderRequest {

    Long userId;
    BigDecimal amount;
    List<OrderItem> items;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
