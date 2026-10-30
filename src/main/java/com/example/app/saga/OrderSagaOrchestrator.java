package com.example.app.saga;

import com.example.app.saga.clients.PaymentClient;
import com.example.app.saga.clients.WarehouseClient;
import com.example.app.saga.domain.Order;
import com.example.app.saga.domain.OrderStatus;
import com.example.app.saga.domain.OutboxEvent;
import com.example.app.saga.domain.SagaState;
import com.example.app.saga.domain.SagaStatus;
import com.example.app.saga.dto.CreateOrderRequest;
import com.example.app.saga.dto.ProcessPaymentRequest;
import com.example.app.saga.dto.ProcessPaymentResponse;
import com.example.app.saga.dto.ReserveStockRequest;
import com.example.app.saga.dto.ReserveStockResponse;
import com.example.app.saga.dto.SagaContext;
import com.example.app.saga.event.OrderCreatedEvent;
import com.example.app.saga.repositories.OrderRepository;
import com.example.app.saga.repositories.OutboxRepository;
import com.example.app.saga.repositories.SagaStateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderSagaOrchestrator {
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final SagaStateRepository sagaStateRepository;
    private final OutboxRepository outboxRepository;
    private final PaymentClient paymentClient;
    private final WarehouseClient warehouseClient;

    public OrderSagaOrchestrator(OrderRepository orderRepository, ObjectMapper objectMapper,
                                 SagaStateRepository sagaStateRepository, OutboxRepository outboxRepository,
                                 PaymentClient paymentClient, WarehouseClient warehouseClient) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
        this.sagaStateRepository = sagaStateRepository;
        this.outboxRepository = outboxRepository;
        this.paymentClient = paymentClient;
        this.warehouseClient = warehouseClient;
    }

    @Transactional
    public Order createOrder(CreateOrderRequest request) throws JsonProcessingException {
        String sagaId = UUID.randomUUID().toString();
        String correlationId = UUID.randomUUID().toString();

        Order order = new Order(request);
        orderRepository.save(order);

        SagaContext ctx = new SagaContext(
                request.getUserId(), request.getAmount(), request.getItems(),
                null, null);
        SagaState state = new SagaState();
        state.setSagaId(sagaId);
        state.setStatus(SagaStatus.PAYMENT_PENDING);
        state.setCurrentStep("PAYMENT");
        state.setContext(objectMapper.writeValueAsString(ctx));
        state.setOrderId(order.getId());
        state.setCreatedAt(Instant.now());
        state.setUpdatedAt(Instant.now());

        sagaStateRepository.save(state);

        outboxRepository.save(buildOutbox(order.getId(), "OrderCreated",
                new OrderCreatedEvent(UUID.randomUUID().toString(), correlationId,
                        order.getId(), request.getUserId(), request.getAmount())));
        try {
            executePayment(sagaId, order.getId(), ctx);
        } catch (Exception e) {
            compensate(sagaId, order.getId(), ctx, e.getMessage());
        }
        return order;
    }

    private void executePayment(String sagaId, Long orderId, SagaContext ctx) throws JsonProcessingException {
        String commandId = "pay-" + sagaId;
        ProcessPaymentResponse resp = paymentClient.processPayment(sagaId, new ProcessPaymentRequest(orderId, ctx.userId(), ctx.amount()));
        if (!"SUCCESS".equals(resp.status())) {
            throw new PaimentFailedException(resp.errorMessage());
        }

        ctx = ctx.withPaymentId(resp.paymentId());
        SagaState state = sagaStateRepository.findById(sagaId).orElseThrow();
        state.setStatus(SagaStatus.STOCK_PENDING);
        state.setCurrentStep("STOCK");
        state.setContext(objectMapper.writeValueAsString(ctx));
        state.setUpdatedAt(Instant.now());
        sagaStateRepository.save(state);

        try {
            executeStock(sagaId, orderId, ctx);
        } catch (Exception e) {
            compensate(sagaId, orderId, ctx, e.getMessage());
        }
    }

    private void executeStock(String sagaId, Long orderId, SagaContext ctx) throws JsonProcessingException {
        String commandId = "stock-" + sagaId;
        ReserveStockResponse resp = warehouseClient.reserve(sagaId, new ReserveStockRequest(orderId, ctx.items()));
        if (!"SUCCESS".equals(resp.status())) {
            throw new StockFailedException(resp.errorMessage());
        }
        ctx = ctx.withReservationId(resp.reservationId());
        completeSaga(sagaId, orderId, ctx);
    }

    private void completeSaga(String sagaId, Long orderId, SagaContext ctx) throws JsonProcessingException {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(OrderStatus.CONFIRMED);
        order.setUpdatedAt(Instant.now());
        orderRepository.save(order);

        SagaState state = sagaStateRepository.findById(sagaId).orElseThrow();
        state.setStatus(SagaStatus.COMPLETED);
        state.setCurrentStep("DONE");
        state.setContext(objectMapper.writeValueAsString(ctx));
        state.setUpdatedAt(Instant.now());
        sagaStateRepository.save(state);

        outboxRepository.save(buildOutbox(order.getId(), "OrderConfirmed",
                new OrderConfirmedEvent(UUID.randomUUID().toString(),
                        order.getCorrelationId(), order.getId())));
    }


    private void compensate(String sagaId, Long orderId, SagaContext ctx, String message) {
        //todo реализовать самостоятельно метод
    }


    private OutboxEvent buildOutbox(Long aggregateId, String type, Object payload) throws JsonProcessingException {
        OutboxEvent e = new OutboxEvent();
        e.setAggregateId(aggregateId);
        e.setEventType(type);
        e.setPayload(objectMapper.writeValueAsString(payload));
        e.setPublished(false);
        e.setCreatedAt(Instant.now());
        return e;
    }

}
