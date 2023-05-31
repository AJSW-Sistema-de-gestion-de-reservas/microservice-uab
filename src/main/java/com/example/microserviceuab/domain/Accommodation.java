package com.example.microserviceuab.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Data
@Document(collection = "accommodation")
public class Accommodation {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Indexed(direction = IndexDirection.DESCENDING)
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private boolean enabled;

    @DocumentReference(lazy = true)
    private Owner owner;
}
