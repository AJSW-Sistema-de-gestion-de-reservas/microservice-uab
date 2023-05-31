package com.example.microserviceuab.repository;

import com.example.microserviceuab.domain.Owner;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
    Optional<Owner> findByUsername(String username);

    @Query("{ 'chatId':  ?0 }")
    Optional<Owner> findByChatId(Long chatId);

    @ExistsQuery("{ 'username':  ?0 }")
    boolean existsByUsername(String username);
}
