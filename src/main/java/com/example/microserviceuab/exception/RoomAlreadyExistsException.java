package com.example.microserviceuab.exception;

public class RoomAlreadyExistsException extends RuntimeException {

    public RoomAlreadyExistsException(String message) {
        super(message);
    }

    public RoomAlreadyExistsException() {
        super();
    }

}
