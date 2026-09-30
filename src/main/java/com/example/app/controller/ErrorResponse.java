package com.example.app.controller;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    private String message;
    private String code;
    private String path;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

    public ErrorResponse(String message, String code, String path, LocalDateTime timestamp) {
        this.message = message;
        this.code = code;
        this.path = path;
        this.timestamp = timestamp;
    }

    public ErrorResponse(String message, String code, String path, LocalDateTime timestamp, Map<String, String> errors) {
        this.message = message;
        this.code = code;
        this.path = path;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
