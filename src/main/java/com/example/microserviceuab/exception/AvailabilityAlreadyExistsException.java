package com.example.microserviceuab.exception;

public class AvailabilityAlreadyExistsException extends RuntimeException {

    public AvailabilityAlreadyExistsException(String message) {
        super(message);
    }

    public AvailabilityAlreadyExistsException() {
        super();
    }

}
