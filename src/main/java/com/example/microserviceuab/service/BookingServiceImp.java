package com.example.microserviceuab.service;

import com.example.microserviceuab.domain.*;
import com.example.microserviceuab.dto.BookingCreationRequestDto;
import com.example.microserviceuab.dto.BookingInfoResponseDto;
import com.example.microserviceuab.repository.AvailabilityRepository;
import com.example.microserviceuab.repository.BookingRepository;
import com.example.microserviceuab.repository.ClientRepository;
import com.example.microserviceuab.utils.TimeUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImp implements BookingService {
    private final BookingRepository bookingRepository;
    private final AvailabilityRepository availabilityRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public BookingServiceImp(BookingRepository bookingRepository, AvailabilityRepository availabilityRepository,
                             ClientRepository clientRepository) {
        this.bookingRepository = bookingRepository;
        this.availabilityRepository = availabilityRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void resgister(String accommodationId, String roomId, BookingCreationRequestDto dto) {
        Client client = clientRepository.findById(dto.getClientId()).orElseThrow(RuntimeException::new);

        List<Availability> availabilities = availabilityRepository.findAllByAccommodationAndRoomAndDateBetween(
                new ObjectId(accommodationId), new ObjectId(roomId),
                TimeUtils.convertInstantDateToUTC(dto.getCheckIn()),
                TimeUtils.convertInstantDateToUTC(dto.getCheckOut())
        );

        for (Availability availability : availabilities) {
            if (availability.getAvailableQuantity() <= 0) {
                throw new RuntimeException();
            } else {
                availability.setAvailableQuantity(availability.getAvailableQuantity() - 1);
            }
        }
        availabilityRepository.saveAll(availabilities);

        Booking booking = Booking.builder()
                .amount(dto.getAmount())
                .paid(false)
                .checkIn(dto.getCheckIn())
                .checkOut(dto.getCheckOut())
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
                .accommodationId(booking.getAccommodation().getId())
                .roomId(booking.getRoom().getId())
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
                .accommodationId(booking.getAccommodation().getId())
                .roomId(booking.getRoom().getId())
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
                .accommodationId(booking.getAccommodation().getId())
                .roomId(booking.getRoom().getId())
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
                .accommodationId(booking.getAccommodation().getId())
                .roomId(booking.getRoom().getId())
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
                .accommodationId(booking.getAccommodation().getId())
                .roomId(booking.getRoom().getId())
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
                .accommodationId(booking.getAccommodation().getId())
                .roomId(booking.getRoom().getId())
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
                .accommodationId(booking.getAccommodation().getId())
                .roomId(booking.getRoom().getId())
                .amount(booking.getAmount())
                .paid(booking.isPaid())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .paymentConfirmedAt(booking.getPaymentConfirmedAt())
                .build()).toList();
    }
}
