package com.example.app.saga;

public class StockFailedException extends RuntimeException {
    public StockFailedException(String s) {
        super(s);
    }
}
