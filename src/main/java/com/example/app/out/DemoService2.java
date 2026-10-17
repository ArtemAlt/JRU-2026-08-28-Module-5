package com.example.app.out;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoService2 {
    private final RestClient restClient;

    public DemoService2(RestClient restClient) {
        this.restClient = restClient;
    }

    public String sayHello() {
        return restClient.get()
                .uri("hello")
                .retrieve()
                .body(String.class);
    }
}
