package com.example.microserviceuab.service;

import com.example.microserviceuab.domain.*;
import com.example.microserviceuab.dto.BookingCreationRequestDto;
import com.example.microserviceuab.dto.BookingInfoResponseDto;
import com.example.microserviceuab.exception.ClientNotFoundException;
import com.example.microserviceuab.exception.NoAvailabilityException;
import com.example.microserviceuab.exception.RoomNotFoundException;
import com.example.microserviceuab.repository.AvailabilityRepository;
import com.example.microserviceuab.repository.BookingRepository;
import com.example.microserviceuab.repository.ClientRepository;
import com.example.microserviceuab.repository.RoomRepository;
import com.example.microserviceuab.utils.TimeUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService {
    private final BookingRepository bookingRepository;
    private final AvailabilityRepository availabilityRepository;
    private final RoomRepository roomRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public BookingServiceImp(BookingRepository bookingRepository,
                             AvailabilityRepository availabilityRepository,
                             RoomRepository roomRepository,
                             ClientRepository clientRepository) {
        this.bookingRepository = bookingRepository;
        this.availabilityRepository = availabilityRepository;
        this.roomRepository = roomRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void register(String accommodationId, String roomId, BookingCreationRequestDto dto) {
        Client client = clientRepository.findById(dto.getClientId()).orElseThrow(ClientNotFoundException::new);
        Room room = roomRepository.findByIdAndAccommodation(roomId, new ObjectId(accommodationId))
                .orElseThrow(RoomNotFoundException::new);

        Instant startInstant = dto.getCheckIn().toInstant();
        Instant endInstant = dto.getCheckOut().toInstant();
        long startMillis = startInstant.toEpochMilli();
        long endMillis = endInstant.toEpochMilli();

        List<Availability> availabilities = new ArrayList<>();
        for (long i = startMillis; i <= endMillis; i += 86400000) {
            Instant instant = Instant.ofEpochMilli(i);

            Optional<Availability> availability = availabilityRepository.findByAccommodationAndRoomAndDate(
                    new ObjectId(accommodationId),
                    new ObjectId(roomId),
                    instant
            );

            Availability av;
            if (availability.isEmpty()) {
                av = Availability.builder()
                        .date(Date.from(instant))
                        .accommodation(Accommodation.builder().id(accommodationId).build())
                        .room(room)
                        .availableQuantity(room.getQuantity() - 1)
                        .build();
            } else {
                av = availability.get();

                if (av.getAvailableQuantity() <= 0)
                    throw new NoAvailabilityException();

                av.setAvailableQuantity(av.getAvailableQuantity() - 1);
            }
            availabilities.add(av);
        }
        availabilityRepository.saveAll(availabilities);

        Booking booking = Booking.builder()
                .amount(0)
                .paid(false)
                .checkIn(Date.from(TimeUtils.convertInstantDateToUTC(dto.getCheckIn())))
                .checkOut(Date.from(TimeUtils.convertInstantDateToUTC(dto.getCheckOut())))
                .createdAt(new Date())
                .paymentConfirmedAt(null)
                .accommodation(Accommodation.builder().id(accommodationId).build())
                .room(Room.builder().id(roomId).build())
                .client(client)
                .build();

        bookingRepository.save(booking);
    }

    @Override
    public List<BookingInfoResponseDto> findAllByAccommodation(String accommodationId) {
        List<Booking> results = bookingRepository.findAllByAccommodation(new ObjectId(accommodationId));

        return results.stream().map(booking -> BookingInfoResponseDto.builder()
                .id(booking.getId())
                .clientId(booking.getClient().getId())
                .clientName(booking.getClient().getFullName())
                .accommodationId(booking.getAccommodation().getId())
                .accommodationName(booking.getAccommodation().getName())
                .roomId(booking.getRoom().getId())
                .roomName(booking.getRoom().getName())
                .amount(booking.getAmount())
                .paid(booking.isPaid())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .paymentConfirmedAt(booking.getPaymentConfirmedAt())
                .build()).toList();
    }

    @Override
    public List<BookingInfoResponseDto> findAllByAccommodationAndDate(String accommodationId, Date date) {
        List<Booking> results = bookingRepository.findAllByAccommodationAndDate(
                new ObjectId(accommodationId), TimeUtils.convertInstantDateToUTC(date)
        );

        return results.stream().map(booking -> BookingInfoResponseDto.builder()
                .id(booking.getId())
                .clientId(booking.getClient().getId())
                .clientName(booking.getClient().getFullName())
                .accommodationId(booking.getAccommodation().getId())
                .accommodationName(booking.getAccommodation().getName())
                .roomId(booking.getRoom().getId())
                .roomName(booking.getRoom().getName())
                .amount(booking.getAmount())
                .paid(booking.isPaid())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .paymentConfirmedAt(booking.getPaymentConfirmedAt())
                .build()).toList();
    }

    @Override
    public List<BookingInfoResponseDto> findAllByAccommodationAndDateBetween(String accommodationId, Date startDate,
                                                                             Date endDate) {
        List<Booking> results = bookingRepository.findAllByAccommodationAndDateBetween(
                new ObjectId(accommodationId),
                TimeUtils.convertInstantDateToUTC(startDate),
                TimeUtils.convertInstantDateToUTC(endDate)
        );

        return results.stream().map(booking -> BookingInfoResponseDto.builder()
                .id(booking.getId())
                .clientId(booking.getClient().getId())
                .clientName(booking.getClient().getFullName())
                .accommodationId(booking.getAccommodation().getId())
                .accommodationName(booking.getAccommodation().getName())
                .roomId(booking.getRoom().getId())
                .roomName(booking.getRoom().getName())
                .amount(booking.getAmount())
                .paid(booking.isPaid())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .paymentConfirmedAt(booking.getPaymentConfirmedAt())
                .build()).toList();
    }

    @Override
    public List<BookingInfoResponseDto> findAllByAccommodationAndRoom(String accommodationId, String roomId) {
        List<Booking> results = bookingRepository.findAllByAccommodationAndRoom(
                new ObjectId(accommodationId), new ObjectId(roomId)
        );

        return results.stream().map(booking -> BookingInfoResponseDto.builder()
                .id(booking.getId())
                .clientId(booking.getClient().getId())
                .clientName(booking.getClient().getFullName())
                .accommodationId(booking.getAccommodation().getId())
                .accommodationName(booking.getAccommodation().getName())
                .roomId(booking.getRoom().getId())
                .roomName(booking.getRoom().getName())
                .amount(booking.getAmount())
                .paid(booking.isPaid())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .paymentConfirmedAt(booking.getPaymentConfirmedAt())
                .build()).toList();
    }

    @Override
    public List<BookingInfoResponseDto> findAllByAccommodationAndRoomAndDate(String accommodationId, String roomId,
                                                                             Date date) {
        List<Booking> results = bookingRepository.findAllByAccommodationAndRoomAndDate(
                new ObjectId(accommodationId),
                new ObjectId(roomId),
                TimeUtils.convertInstantDateToUTC(date)
        );

        return results.stream().map(booking -> BookingInfoResponseDto.builder()
                .id(booking.getId())
                .clientId(booking.getClient().getId())
                .clientName(booking.getClient().getFullName())
                .accommodationId(booking.getAccommodation().getId())
                .accommodationName(booking.getAccommodation().getName())
                .roomId(booking.getRoom().getId())
                .roomName(booking.getRoom().getName())
                .amount(booking.getAmount())
                .paid(booking.isPaid())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .paymentConfirmedAt(booking.getPaymentConfirmedAt())
                .build()).toList();
    }

    @Override
    public List<BookingInfoResponseDto> findAllByAccommodationAndRoomAndDateBetween(String accommodationId,
                                                                                    String roomId,
                                                                                    Date startDate,
                                                                                    Date endDate) {
        List<Booking> results = bookingRepository.findAllByAccommodationAndRoomAndDateBetween(
                new ObjectId(accommodationId),
                new ObjectId(roomId),
                TimeUtils.convertInstantDateToUTC(startDate),
                TimeUtils.convertInstantDateToUTC(endDate)
        );

        return results.stream().map(booking -> BookingInfoResponseDto.builder()
                .id(booking.getId())
                .clientId(booking.getClient().getId())
                .clientName(booking.getClient().getFullName())
                .accommodationId(booking.getAccommodation().getId())
                .accommodationName(booking.getAccommodation().getName())
                .roomId(booking.getRoom().getId())
                .roomName(booking.getRoom().getName())
                .amount(booking.getAmount())
                .paid(booking.isPaid())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .paymentConfirmedAt(booking.getPaymentConfirmedAt())
                .build()).toList();
    }

    @Override
    public List<BookingInfoResponseDto> findAllByClient(String clientId) {
        List<Booking> results = bookingRepository.findAllByClient(new ObjectId(clientId));

        return results.stream().map(booking -> BookingInfoResponseDto.builder()
                .id(booking.getId())
                .clientId(booking.getClient().getId())
                .clientName(booking.getClient().getFullName())
                .accommodationId(booking.getAccommodation().getId())
                .accommodationName(booking.getAccommodation().getName())
                .roomId(booking.getRoom().getId())
                .roomName(booking.getRoom().getName())
                .amount(booking.getAmount())
                .paid(booking.isPaid())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .paymentConfirmedAt(booking.getPaymentConfirmedAt())
                .build()).toList();
    }
}
