package com.example.microserviceuab.controller;

import com.example.microserviceuab.dto.AvailabilityCreateRequestDto;
import com.example.microserviceuab.dto.AvailabilityInfoResponseDto;
import com.example.microserviceuab.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AvailabilityController {
    private final AvailabilityService service;

    @Autowired
    public AvailabilityController(AvailabilityService service) {
        this.service = service;
    }

    @PostMapping("api/accommodation/{accommodationId}/room/{roomId}/availability")
    public ResponseEntity<String> registerByAccommodationIdAndRoomId(@PathVariable String accommodationId,
                                                                     @PathVariable String roomId,
                                                                     @RequestBody AvailabilityCreateRequestDto dto) {
        service.register(accommodationId, roomId, dto);
        return ResponseEntity.ok("");
    }

    @GetMapping("api/accommodation/{accommodationId}/room/{roomId}/availability")
    public ResponseEntity<List<AvailabilityInfoResponseDto>> getByAccommodationIdAndRoomId(
            @PathVariable String accommodationId,
            @PathVariable String roomId) {
        List<AvailabilityInfoResponseDto> results = service.findByAccommodationIdAndRoomId(accommodationId, roomId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("api/accommodation/{accommodationId}/room/{roomId}/availability/{date}")
    public ResponseEntity<AvailabilityInfoResponseDto> getByAccommodationIdRoomIdAndDate(
            @PathVariable String accommodationId,
            @PathVariable String roomId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        AvailabilityInfoResponseDto result = service.findByAccommodationIdRoomIdAndDate(accommodationId, roomId, date);
        return ResponseEntity.ok(result);
    }

    @GetMapping("api/accommodation/{accommodationId}/room/{roomId}/availability/{start}/{end}")
    public ResponseEntity<List<AvailabilityInfoResponseDto>> getByAccommodationIdRoomIdAndDates(
            @PathVariable String accommodationId,
            @PathVariable String roomId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        List<AvailabilityInfoResponseDto> results = service.findByAccommodationIdRoomIdAndDates(
                accommodationId, roomId, start, end
        );

        return ResponseEntity.ok(results);
    }

    @GetMapping("api/accommodation/{accommodationId}/availability")
    public ResponseEntity<List<AvailabilityInfoResponseDto>> getByAccommodationId(@PathVariable String accommodationId) {
        List<AvailabilityInfoResponseDto> results = service.findByAccommodationId(accommodationId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("api/accommodation/{accommodationId}/availability/{date}")
    public ResponseEntity<List<AvailabilityInfoResponseDto>> getByAccommodationIdAndDate(
            @PathVariable String accommodationId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<AvailabilityInfoResponseDto> results = service.findByAccommodationIdAndDate(accommodationId, date);
        return ResponseEntity.ok(results);
    }

    @GetMapping("api/accommodation/{accommodationId}/availability/{startDate}/{endDate}")
    public ResponseEntity<List<AvailabilityInfoResponseDto>> getByAccommodationIdAndDate(
            @PathVariable String accommodationId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<AvailabilityInfoResponseDto> results = service.findByAccommodationIdAndDates(
                accommodationId, startDate, endDate
        );
        return ResponseEntity.ok(results);
    }
}
