package com.example.app.security.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ExternalApiHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return null;
    }
}
