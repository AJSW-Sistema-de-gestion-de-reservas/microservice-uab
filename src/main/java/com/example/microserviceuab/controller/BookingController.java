package com.example.microserviceuab.controller;

import com.example.microserviceuab.dto.BookingCreationRequestDto;
import com.example.microserviceuab.dto.BookingInfoResponseDto;
import com.example.microserviceuab.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BookingController {
    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("api/accommodation/{accommodationId}/room/{roomId}/booking")
    public ResponseEntity<String> register(@PathVariable String accommodationId,
                                           @PathVariable String roomId,
                                           @RequestBody BookingCreationRequestDto dto) {
        service.resgister(accommodationId, roomId, dto);
        return ResponseEntity.ok("");
    }

    @GetMapping("api/accommodation/{accommodationId}/booking")
    public ResponseEntity<List<BookingInfoResponseDto>> getAllByAccommodation(@PathVariable String accommodationId) {
        List<BookingInfoResponseDto> results = service.findAllByAccommodation(accommodationId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("api/accommodation/{accommodationId}/booking/{date}")
    public ResponseEntity<List<BookingInfoResponseDto>> getAllByAccommodationAndDate(
            @PathVariable String accommodationId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<BookingInfoResponseDto> results = service.findAllByAccommodationAndDate(accommodationId, date);
        return ResponseEntity.ok(results);
    }

    @GetMapping("api/accommodation/{accommodationId}/booking/{startDate}/{endDate}")
    public ResponseEntity<List<BookingInfoResponseDto>> getAllByAccommodationAndDateBetween(
            @PathVariable String accommodationId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<BookingInfoResponseDto> results = service.findAllByAccommodationAndDateBetween(accommodationId, startDate, endDate);
        return ResponseEntity.ok(results);
    }

    @GetMapping("api/accommodation/{accommodationId}/room/{roomId}/booking")
    public ResponseEntity<List<BookingInfoResponseDto>> getAllByAccommodationAndRoom(
            @PathVariable String accommodationId,
            @PathVariable String roomId) {
        List<BookingInfoResponseDto> results = service.findAllByAccommodationAndRoom(accommodationId, roomId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("api/accommodation/{accommodationId}/room/{roomId}/booking/{date}")
    public ResponseEntity<List<BookingInfoResponseDto>> getAllByAccommodationAndRoomAndDate(
            @PathVariable String accommodationId,
            @PathVariable String roomId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<BookingInfoResponseDto> results = service.findAllByAccommodationAndRoomAndDate(accommodationId, roomId, date);
        return ResponseEntity.ok(results);
    }

    @GetMapping("api/accommodation/{accommodationId}/room/{roomId}/booking/{startDate}/{endDate}")
    public ResponseEntity<List<BookingInfoResponseDto>> getAllByAccommodationAndRoomAndBetweenDates(
            @PathVariable String accommodationId,
            @PathVariable String roomId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<BookingInfoResponseDto> results = service.findAllByAccommodationAndRoomAndDateBetween(accommodationId, roomId, startDate, endDate);
        return ResponseEntity.ok(results);
    }

    @GetMapping("api/client/{clientId}/booking")
    public ResponseEntity<List<BookingInfoResponseDto>> getAllByClient(@PathVariable String clientId) {
        List<BookingInfoResponseDto> results = service.findAllByClient(clientId);
        return ResponseEntity.ok(results);
    }
}
