package com.example.microserviceuab.exception;

public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(String message) {
        super(message);
    }

    public OwnerNotFoundException() {
        super();
    }
    
}
