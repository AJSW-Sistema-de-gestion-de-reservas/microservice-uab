package com.example.microserviceuab.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.ZonedDateTime;

@Builder
@Data
@Document(collection = "booking")
public class Booking {
    @Id
    private String id;
    private boolean paid;
    private ZonedDateTime checkIn;
    private ZonedDateTime checkOut;

    @DocumentReference(lazy = true)
    private Owner owner;

    @DocumentReference(lazy = true)
    private Client client;

    @DocumentReference
    private Accommodation accommodation;

    @DocumentReference
    private Room room;
}
