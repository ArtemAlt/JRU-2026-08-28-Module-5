package com.example.app;

import com.example.app.service.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.app")
public class AppConfig {

    @Bean
    public AuthorizationService authorizationService() {
        return new AuthorizationService();
    }
}
