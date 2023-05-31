package com.example.microserviceuab.dto;

import lombok.Data;

@Data
public class ClientCreationRequestDto {
    private String username;
    private String firstName;
    private String lastName;
    private long chatId;
}
