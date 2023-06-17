package com.example.microserviceuab.exception;

public class NoAvailabilityException extends RuntimeException {

    public NoAvailabilityException(String message) {
        super(message);
    }

    public NoAvailabilityException() {
        super();
    }

}
