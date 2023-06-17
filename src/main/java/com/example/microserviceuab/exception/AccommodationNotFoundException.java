package com.example.microserviceuab.exception;

public class AccommodationNotFoundException extends RuntimeException {

    public AccommodationNotFoundException(String message) {
        super(message);
    }

    public AccommodationNotFoundException() {
        super();
    }

}
