package com.example.app.order;

import com.example.app.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@Sql(scripts = "test/data",
//        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class OrderServiceTest extends AbstractIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        String customerName = "James";
        BigDecimal orderPrice = new BigDecimal("100.00");
        Order order = orderService.createOrder(customerName, orderPrice);
        assertNotNull(order);
        assertEquals(customerName, order.getCustomerName());
        assertEquals(order.getTotalAmount(), orderPrice);
    }

    @Test
    public void testFindByCustomerName() {
        String customerName = "James1";
        orderService.createOrder(customerName, new BigDecimal("100.00"));

        List<Order> byCustomer = orderService.findByCustomer(customerName);
        assertNotNull(byCustomer);
        assertEquals(1, byCustomer.size());
    }

}