package com.example.microserviceuab.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoomInfoResponseDto {
    private String id;
    private String name;
    private int maxPeople;
    private int quantity;
    private double price;
}
