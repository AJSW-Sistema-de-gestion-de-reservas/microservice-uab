package com.example.microserviceuab.service;

import com.example.microserviceuab.domain.Accommodation;
import com.example.microserviceuab.domain.Owner;
import com.example.microserviceuab.dto.AccommodationCreationWithChatIdRequestDto;
import com.example.microserviceuab.dto.AccommodationCreationWithIdRequestDto;
import com.example.microserviceuab.dto.AccommodationInfoResponseDto;
import com.example.microserviceuab.dto.AccommodationUpdateRequestDto;
import com.example.microserviceuab.repository.AccommodationRepository;
import com.example.microserviceuab.repository.OwnerRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccommodationServiceImp implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final OwnerRepository ownerRepository;

    public AccommodationServiceImp(AccommodationRepository accommodationRepository, OwnerRepository ownerRepository) {
        this.accommodationRepository = accommodationRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void create(AccommodationCreationWithChatIdRequestDto dto) {
        Owner owner = ownerRepository.findByChatId(123123123L).orElseThrow(RuntimeException::new);
        saveAccommodation(owner, dto.getName(), dto.getAddress(), dto.getCity(), dto.getProvince(), dto.getPostalCode());
    }

    @Override
    public void create(AccommodationCreationWithIdRequestDto dto) {
        Owner owner = ownerRepository.findById(dto.getOwnerId()).orElseThrow(RuntimeException::new);
        saveAccommodation(owner, dto.getName(), dto.getAddress(), dto.getCity(), dto.getProvince(), dto.getPostalCode());
    }

    private void saveAccommodation(Owner owner, String name, String address, String city, String province, String postalCode) {
        Accommodation accommodation = Accommodation.builder()
                .owner(owner)
                .name(name)
                .address(address)
                .city(city)
                .province(province)
                .postalCode(postalCode)
                .enabled(true)
                .build();

        accommodationRepository.save(accommodation);
    }

    @Override
    public void update(String id, AccommodationUpdateRequestDto dto) {
        Owner owner = ownerRepository.findById(dto.getOwnerId()).orElseThrow(RuntimeException::new);

        accommodationRepository.findById(id)
                .ifPresentOrElse((accommodation) -> {
                    if (Objects.equals(owner.getId(), dto.getOwnerId())) {
                        accommodation.setName(dto.getName());
                        accommodation.setAddress(dto.getAddress());
                        accommodation.setCity(dto.getCity());
                        accommodation.setProvince(dto.getProvince());
                    }
                }, () -> {
                    throw new RuntimeException();
                });
    }

    @Override
    public List<AccommodationInfoResponseDto> findByName(String name) {
        List<Accommodation> resultList = accommodationRepository.findByName(name);

        return resultList.stream().map(a -> AccommodationInfoResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .address(a.getAddress())
                .city(a.getCity())
                .province(a.getProvince())
                .postalCode(a.getPostalCode())
                .build()).toList();
    }

    @Override
    public List<AccommodationInfoResponseDto> findByOwnerId(String ownerId) {
        List<Accommodation> resultList = accommodationRepository.findByOwnerId(new ObjectId(ownerId));

        return resultList.stream().map(a -> AccommodationInfoResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .address(a.getAddress())
                .city(a.getCity())
                .province(a.getProvince())
                .postalCode(a.getPostalCode())
                .build()).toList();
    }

    @Override
    public List<AccommodationInfoResponseDto> findByCity(String city) {
        List<Accommodation> resultList = accommodationRepository.findByCity(city);

        return resultList.stream().map(a -> AccommodationInfoResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .address(a.getAddress())
                .city(a.getCity())
                .province(a.getProvince())
                .postalCode(a.getPostalCode())
                .build()).toList();
    }

    @Override
    public List<AccommodationInfoResponseDto> findByProvince(String province) {
        List<Accommodation> resultList = accommodationRepository.findByProvince(province);

        return resultList.stream().map(a -> AccommodationInfoResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .address(a.getAddress())
                .city(a.getCity())
                .province(a.getProvince())
                .postalCode(a.getPostalCode())
                .build()).toList();
    }
}
