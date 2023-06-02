package com.example.microserviceuab.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
@Document(collection = "room")
public class Room {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private int quantity;
    private boolean enabled;

    @DocumentReference(lazy = true)
    private Accommodation accommodation;
}
