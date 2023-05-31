package com.example.microserviceuab.controller;

import com.example.microserviceuab.dto.AccommodationCreationWithChatIdRequestDto;
import com.example.microserviceuab.dto.AccommodationCreationWithIdRequestDto;
import com.example.microserviceuab.dto.AccommodationInfoResponseDto;
import com.example.microserviceuab.dto.AccommodationUpdateRequestDto;
import com.example.microserviceuab.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accommodation")
public class AccommodationController {
    private final AccommodationService service;

    @Autowired
    public AccommodationController(AccommodationService service) {
        this.service = service;
    }

    @PostMapping("create/chatId")
    public ResponseEntity<String> createWithChatId(@RequestBody AccommodationCreationWithChatIdRequestDto dto) {
        service.create(dto);
        return ResponseEntity.ok("");
    }

    @PostMapping("create/id")
    public ResponseEntity<String> createWithId(@RequestBody AccommodationCreationWithIdRequestDto dto) {
        service.create(dto);
        return ResponseEntity.ok("");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody AccommodationUpdateRequestDto dto) {
        service.update(id, dto);
        return ResponseEntity.ok("");
    }

    @GetMapping("search/owner/{ownerId}")
    public ResponseEntity<List<AccommodationInfoResponseDto>> findByOwnerId(@PathVariable String ownerId) {
        List<AccommodationInfoResponseDto> result = service.findByOwnerId(ownerId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search/name/{name}")
    public ResponseEntity<List<AccommodationInfoResponseDto>> findByName(@PathVariable String name) {
        List<AccommodationInfoResponseDto> result = service.findByName(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search/city/{city}")
    public ResponseEntity<List<AccommodationInfoResponseDto>> findByCity(@PathVariable String city) {
        List<AccommodationInfoResponseDto> result = service.findByCity(city);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search/province/{province}")
    public ResponseEntity<List<AccommodationInfoResponseDto>> findByProvince(@PathVariable String province) {
        List<AccommodationInfoResponseDto> result = service.findByProvince(province);
        return ResponseEntity.ok(result);
    }
}
