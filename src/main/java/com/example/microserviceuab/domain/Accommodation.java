package com.example.microserviceuab.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Builder
@Data
@Document(collection = "accommodation")
public class Accommodation {
    @Id
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

    @DocumentReference(lazy = true)
    private List<Room> rooms;
}
