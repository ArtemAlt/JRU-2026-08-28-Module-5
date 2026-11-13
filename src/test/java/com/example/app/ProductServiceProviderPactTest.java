package com.example.app;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@Provider("product-service")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceProviderPactTest {
    @LocalServerPort
    int port;

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void verifyPact(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("product with id 1 exists")
    void setupProduct() {
        // подготовить данные в БД: productRepository.save(new Product(1L, "Book", 10.0));
    }
}
