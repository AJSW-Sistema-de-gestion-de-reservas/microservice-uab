package com.example.microserviceuab.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@SuperBuilder
@Data
@NoArgsConstructor
@Document
public class User {
    @Id
    protected String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    protected String username;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    protected long chatId;
    protected String firstName;
    protected String lastName;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}