package com.example.app.saga.clients;

import com.example.app.saga.dto.ProcessPaymentRequest;
import com.example.app.saga.dto.ProcessPaymentResponse;
import com.example.app.saga.dto.RefundRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping("/payments")
    ProcessPaymentResponse processPayment(@RequestHeader("X-Command-Id") String commandId,
                                          @RequestBody ProcessPaymentRequest request);

    @PostMapping("/payments/refund")
    void refund(@RequestHeader("X-Command-Id") String commandId,
                @RequestBody RefundRequest request);
}