package com.example.app.exeptions;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String resource, Long id) {
        super("NOT_FOUND", resource + " with id " + id);
    }
}
