package com.example.microserviceuab.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccommodationInfoResponseDto {
    private String id;
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}
