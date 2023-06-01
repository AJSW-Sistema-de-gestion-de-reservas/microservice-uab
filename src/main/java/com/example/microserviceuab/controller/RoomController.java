package com.example.microserviceuab.controller;

import com.example.microserviceuab.dto.RoomCreationRequestDto;
import com.example.microserviceuab.dto.RoomInfoResponseDto;
import com.example.microserviceuab.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accommodation/{id}")
public class RoomController {
    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping("room")
    public ResponseEntity<String> create(@PathVariable String id, @RequestBody RoomCreationRequestDto dto) {
        service.create(id, dto);
        return ResponseEntity.ok("");
    }

    @GetMapping("rooms")
    public ResponseEntity<List<RoomInfoResponseDto>> getByAccommodationId(@PathVariable String id) {
        List<RoomInfoResponseDto> rooms = service.getByAccommodationId(id);
        return ResponseEntity.ok(rooms);
    }
}
