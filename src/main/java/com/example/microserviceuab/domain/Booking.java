package com.example.microserviceuab.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.ZonedDateTime;

@Builder
@Data
@Document(collection = "booking")
public class Booking {
    @MongoId(FieldType.OBJECT_ID)
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
