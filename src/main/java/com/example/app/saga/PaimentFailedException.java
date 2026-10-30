package com.example.app.saga;

public class PaimentFailedException extends RuntimeException {

    public PaimentFailedException(String s) {
        super(s);
    }
}
