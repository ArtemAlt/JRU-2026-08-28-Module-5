package com.example.app.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCustomerName(String customerName);
    List<Order> findByTotalAmountGreaterThan(BigDecimal totalAmount);

    @Query("SELECT o FROM Order o WHERE o.status = :status AND o.totalAmount > :minAmount")
    List<Order> findHeightValueOrders(
            @Param("status") OrderStatus status,
            @Param("minAmount") BigDecimal minAmount
    );
}
