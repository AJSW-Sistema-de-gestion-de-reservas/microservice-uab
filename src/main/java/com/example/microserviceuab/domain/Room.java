package com.example.microserviceuab.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "room")
public class Room {
    @Id
    private String id;
    private String name;
    private String type;
    private boolean enabled;
}
