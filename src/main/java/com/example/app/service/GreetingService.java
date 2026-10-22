package com.example.app.service;

import com.example.app.out.HelloClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@ConfigurationProperties(prefix = "app.greeting")
public class GreetingService {
    private String message;
    private Integer count;
    private Boolean enabled;
    private List<String> services;
    private final HelloClient helloClient;

    public GreetingService(HelloClient helloClient) {
        this.helloClient = helloClient;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    private Map<String, String> properties;

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public String greet() {
        if (!enabled)  return "Service disabled";
        return message.repeat(count);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void someMethod() {
        System.out.println("Hello World");
        String hello = helloClient.hello();
        System.out.println(hello);
    }
}
