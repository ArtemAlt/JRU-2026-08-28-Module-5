package com.example.app.out;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoService {
    private final RestTemplate restTemplate;
    public DemoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sayHello() {
        String url = "http://localhost:8080/hello";
        return restTemplate.getForObject(url, String.class);
    }
}
