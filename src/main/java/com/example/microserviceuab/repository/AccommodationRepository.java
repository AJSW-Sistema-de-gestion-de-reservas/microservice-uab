package com.example.microserviceuab.repository;

import com.example.microserviceuab.domain.Accommodation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends MongoRepository<Accommodation, String> {
    @Query("{ name: {'$regex' : ?0, '$options' : 'i'}, enabled: true }")
    List<Accommodation> findByName(String name);

    @Query("{ owner: ?0 }")
    List<Accommodation> findByOwnerId(ObjectId ownerId);

    @Query("{ city:  {'$regex' : ?0, '$options' : 'i'}, enabled: true }")
    List<Accommodation> findByCity(String city);

    @Query("{ province:  ?0, enabled: true }")
    List<Accommodation> findByProvince(String province);
}
