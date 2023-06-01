package com.example.microserviceuab.service;

import com.example.microserviceuab.dto.AccommodationCreationWithChatIdRequestDto;
import com.example.microserviceuab.dto.AccommodationCreationWithIdRequestDto;
import com.example.microserviceuab.dto.AccommodationInfoResponseDto;
import com.example.microserviceuab.dto.AccommodationUpdateRequestDto;

import java.util.List;

public interface AccommodationService {
    void create(AccommodationCreationWithChatIdRequestDto dto);

    void create(AccommodationCreationWithIdRequestDto dto);

    void update(String id, AccommodationUpdateRequestDto dto);

    List<AccommodationInfoResponseDto> findByOwnerId(String ownerId);

    List<AccommodationInfoResponseDto> findByName(String name);

    List<AccommodationInfoResponseDto> findByCity(String city);

    List<AccommodationInfoResponseDto> findByProvince(String province);

    List<AccommodationInfoResponseDto> findByNameCityAndProvince(String name, String city, String province);
}
