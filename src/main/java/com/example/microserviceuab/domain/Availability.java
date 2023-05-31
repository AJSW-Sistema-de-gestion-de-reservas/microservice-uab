package com.example.microserviceuab.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Builder
@Data
@Document(collection = "availability")
public class Availability {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private Date date;
    private int availableQuantity;

    @DocumentReference
    private String hotelId;

    @DocumentReference
    private String roomId;
}
