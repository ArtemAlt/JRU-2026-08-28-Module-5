package com.example.app.order;

import com.example.app.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testFindByCustomerName() {
        String customerName = "James";
        orderRepository.saveAll(List.of(
                new OrderSlim(customerName, new BigDecimal("100.00")),
                new OrderSlim(customerName, new BigDecimal("250.00")),
                new OrderSlim("Jon", new BigDecimal("300.00"))
        ));

        List<OrderSlim> orders = orderRepository.findByCustomerName(customerName);

        assertThat(orders).hasSize(2);
        assertThat(orders.get(0).getCustomerName()).isEqualTo(customerName);
    }


}