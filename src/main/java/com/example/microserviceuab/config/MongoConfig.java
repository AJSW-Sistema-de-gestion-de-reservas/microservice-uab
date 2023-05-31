package com.example.microserviceuab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
    private final String uri;

    @Autowired
    public MongoConfig(@Value("${spring.data.mongodb.uri}") String uri) {
        this.uri = uri;
    }

    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(uri) {

        };
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
