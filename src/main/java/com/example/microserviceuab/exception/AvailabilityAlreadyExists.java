package com.example.microserviceuab.exception;

public class AvailabilityAlreadyExists extends RuntimeException {

    public AvailabilityAlreadyExists(String message) {
        super(message);
    }

    public AvailabilityAlreadyExists() {
        super();
    }

}
