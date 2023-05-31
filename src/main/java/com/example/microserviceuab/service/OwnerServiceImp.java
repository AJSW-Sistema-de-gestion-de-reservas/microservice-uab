package com.example.microserviceuab.service;

import com.example.microserviceuab.domain.Owner;
import com.example.microserviceuab.dto.OwnerCreationRequestDto;
import com.example.microserviceuab.dto.OwnerInfoResponseDto;
import com.example.microserviceuab.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImp implements OwnerService {
    private final OwnerRepository repository;

    @Autowired
    public OwnerServiceImp(OwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void register(OwnerCreationRequestDto dto) {
        if (repository.existsByUsername(dto.getUsername()))
            throw new RuntimeException();

        Owner owner = Owner.builder()
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .chatId(dto.getChatId())
                .build();

        repository.save(owner);
    }

    @Override
    public OwnerInfoResponseDto getInfoFromChatId(Long chatId) {
        Optional<Owner> result = repository.findByChatId(chatId);

        if (result.isEmpty())
            throw new RuntimeException();

        Owner owner = result.get();

        return OwnerInfoResponseDto.builder()
                .id(owner.getId())
                .username(owner.getUsername())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .chatId(owner.getChatId())
                .build();
    }

    @Override
    public OwnerInfoResponseDto getInfoFromUsername(String username) {
        Optional<Owner> result = repository.findByUsername(username);

        if (result.isEmpty())
            throw new RuntimeException();

        Owner owner = result.get();

        return OwnerInfoResponseDto.builder()
                .id(owner.getId())
                .username(owner.getUsername())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .chatId(owner.getChatId())
                .build();
    }

    @Override
    public OwnerInfoResponseDto getInfoFromId(String id) {
        Optional<Owner> result = repository.findById(id);

        if (result.isEmpty())
            throw new RuntimeException();

        Owner owner = result.get();

        return OwnerInfoResponseDto.builder()
                .id(owner.getId())
                .username(owner.getUsername())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .chatId(owner.getChatId())
                .build();
    }
}
