package com.example.microserviceuab.service;

import com.example.microserviceuab.dto.OwnerCreationRequestDto;
import com.example.microserviceuab.dto.OwnerInfoResponseDto;

public interface OwnerService {
    void register(OwnerCreationRequestDto dto);
    OwnerInfoResponseDto getInfoFromChatId(Long chatId);
    OwnerInfoResponseDto getInfoFromUsername(String username);
    OwnerInfoResponseDto getInfoFromId(String id);
}
