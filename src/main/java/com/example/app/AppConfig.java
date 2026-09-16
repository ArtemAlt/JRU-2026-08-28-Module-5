package com.example.app;

import com.example.app.service.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.example.app")
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public AuthorizationService authorizationService() {
        return new AuthorizationService();
    }
}
