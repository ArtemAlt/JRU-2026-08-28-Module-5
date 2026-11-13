package com.example.app;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "product-service", port = "8081")
class ProductServiceConsumerPactTest {

    @Pact(consumer = "order-service")
    public RequestResponsePact getProductPact(PactDslWithProvider builder) {
        return builder
                .given("product with id 1 exists")
                .uponReceiving("get product by id")
                .path("/products/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody()
                        .numberType("id", 1L)
                        .stringType("name", "Book")
                        .decimalType("price", 10.0))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getProductPact")
    void shouldGetProduct(MockServer mockServer) {

    }
}