package com.example.microserviceuab.service;

import com.example.microserviceuab.domain.Accommodation;
import com.example.microserviceuab.domain.Room;
import com.example.microserviceuab.dto.RoomCreationRequestDto;
import com.example.microserviceuab.dto.RoomInfoResponseDto;
import com.example.microserviceuab.repository.AccommodationRepository;
import com.example.microserviceuab.repository.RoomRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImp implements RoomService {
    private final RoomRepository roomRepository;
    private final AccommodationRepository accommodationRepository;

    @Autowired
    public RoomServiceImp(RoomRepository roomRepository, AccommodationRepository accommodationRepository) {
        this.roomRepository = roomRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public void create(String accommodationId, RoomCreationRequestDto dto) {
        if (!accommodationRepository.existsById(accommodationId))
            throw new RuntimeException();

        if (roomRepository.existsByName(dto.getName()))
            throw new RuntimeException();

        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(RuntimeException::new);

        Room room = Room.builder()
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .enabled(true)
                .accommodation(accommodation)
                .build();

        roomRepository.save(room);
    }

    @Override
    public List<RoomInfoResponseDto> getByAccommodationId(String accommodationId) {
        List<Room> results = roomRepository.findAllByAccommodation(new ObjectId(accommodationId));

        return results.stream().map(room -> {
            return RoomInfoResponseDto.builder()
                    .id(room.getId())
                    .name(room.getId())
                    .quantity(room.getQuantity())
                    .build();
        }).toList();
    }
}
