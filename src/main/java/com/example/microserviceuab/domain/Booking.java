package com.example.microserviceuab.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Builder
@Data
@Document(collection = "booking")
public class Booking {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private double amount;
    private boolean paid;
    @Indexed(direction = IndexDirection.DESCENDING)
    private Date checkIn;
    @Indexed(direction = IndexDirection.DESCENDING)
    private Date checkOut;
    private Date createdAt;
    private Date paymentConfirmedAt;

    @DocumentReference(lazy = true)
    private Client client;

    @DocumentReference(lazy = true)
    private Accommodation accommodation;

    @DocumentReference(lazy = true)
    private Room room;
}
