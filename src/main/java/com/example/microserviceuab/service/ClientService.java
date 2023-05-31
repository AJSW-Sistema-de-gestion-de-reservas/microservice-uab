package com.example.microserviceuab.service;

import com.example.microserviceuab.dto.ClientCreationRequestDto;
import com.example.microserviceuab.dto.ClientInfoResponseDto;

public interface ClientService {
    void register(ClientCreationRequestDto dto);
    ClientInfoResponseDto getInfoFromChatId(Long chatId);
    ClientInfoResponseDto getInfoFromUsername(String username);
    ClientInfoResponseDto getInfoFromId(String id);
}
