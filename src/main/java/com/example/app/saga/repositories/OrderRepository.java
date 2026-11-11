package com.example.app.saga.repositories;

import com.example.app.saga.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

