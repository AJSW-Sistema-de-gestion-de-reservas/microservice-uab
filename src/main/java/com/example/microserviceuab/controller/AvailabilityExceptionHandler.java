package com.example.microserviceuab.controller;

import com.example.microserviceuab.dto.ExceptionResponseDto;
import com.example.microserviceuab.exception.AvailabilityAlreadyExists;
import com.example.microserviceuab.exception.AvailabilityNotFoundException;
import com.example.microserviceuab.exception.RoomNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AvailabilityController.class)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailabilityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvailabilityExceptionHandler.class);

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleRoomNotFound(RoomNotFoundException ex) {
        LOGGER.error("handleRoomNotFound", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("Room not found to create a new availability data for that date"),
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(AvailabilityNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleAvailabilityNotFound(AvailabilityNotFoundException ex) {
        LOGGER.error("handleAvailabilityNotFound", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("No availability data found for that date"),
                HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value())
        );
    }

    @ExceptionHandler(AvailabilityAlreadyExists.class)
    public ResponseEntity<ExceptionResponseDto> handleAvailabilityAlreadyExists(AvailabilityAlreadyExists ex) {
        LOGGER.error("handle");
        return new ResponseEntity<>(
                new ExceptionResponseDto("Availability data for that date already exists"),
                HttpStatusCode.valueOf(HttpStatus.CONFLICT.value())
        );
    }

}
