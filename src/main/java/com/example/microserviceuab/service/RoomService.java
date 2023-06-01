package com.example.microserviceuab.service;

import com.example.microserviceuab.dto.RoomCreationRequestDto;
import com.example.microserviceuab.dto.RoomInfoResponseDto;

import java.util.List;

public interface RoomService {
    void create(String accommodationId, RoomCreationRequestDto dto);

    List<RoomInfoResponseDto> getByAccommodationId(String accommodationId);
}
