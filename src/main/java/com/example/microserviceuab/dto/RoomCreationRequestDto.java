package com.example.microserviceuab.dto;

import lombok.Data;

@Data
public class RoomCreationRequestDto {
    private String name;
    private int maxPeople;
    private int quantity;
}
