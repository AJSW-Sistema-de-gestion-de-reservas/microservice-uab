package com.example.microserviceuab.exception;

public class AvailabilityNotFoundException extends RuntimeException {

    public AvailabilityNotFoundException(String message) {
        super(message);
    }

    public AvailabilityNotFoundException() {
        super();
    }

}
