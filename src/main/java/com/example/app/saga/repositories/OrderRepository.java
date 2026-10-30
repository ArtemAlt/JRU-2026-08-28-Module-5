package com.example.app.saga.repositories;

import com.example.app.saga.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}

