package com.example.app.saga.service;

import com.example.app.saga.domain.Order;
import org.springframework.transaction.annotation.Transactional;

public class OrderService {

//    @Transactional
//    public void createOrder(CreateOrderRequest request) {
//        orderRepository.save(new Order());
////        kafakaTemplate.send("order-create", new OrderCreateEvent());
////        outBoxRepository.save(new OrderCreateEvent());
//        applicationEventPublisher.publishEvent(new OrderCreatedEvent());
//        //вылетает исключение
//    }
}
