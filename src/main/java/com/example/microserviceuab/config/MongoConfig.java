package com.example.microserviceuab.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.lang.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories("com.example.microserviceuab.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final String uri;
    private final String database;

    public MongoConfig(@Value("${spring.data.mongodb.uri}") String uri,
                       @Value("${spring.data.mongodb.database}") String database) {
        this.uri = uri;
        this.database = database;
    }

    @NonNull
    @Override
    protected String getDatabaseName() {
        return database;
    }

    @NonNull
    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(uri);

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public boolean autoIndexCreation() {
        return true;
    }

}