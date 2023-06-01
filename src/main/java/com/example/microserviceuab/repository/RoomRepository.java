package com.example.microserviceuab.repository;

import com.example.microserviceuab.domain.Room;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
    @Query("{ accommodation: ?0 }")
    List<Room> findByAccommodationId(ObjectId accommodationId);

    @ExistsQuery("{ name:  ?0 }")
    boolean existsByName(String name);
}
