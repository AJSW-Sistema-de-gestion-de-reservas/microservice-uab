package com.example.microserviceuab.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class AvailabilityCreateRequestDto {
    private Date date;
}
