package com.example.microserviceuab.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class BookingInfoResponseDto {
    private String id;
    private String clientId;
    private String accommodationId;
    private String roomId;
    private double amount;
    private boolean paid;
    private Date checkIn;
    private Date checkOut;
    private Date createdAt;
    private Date paymentConfirmedAt;
}
