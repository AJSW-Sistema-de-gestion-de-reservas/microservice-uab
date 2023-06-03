package com.example.microserviceuab.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingCreationRequestDto {
    private double amount;
    private Date checkIn;
    private Date checkOut;
    private String clientId;
}
