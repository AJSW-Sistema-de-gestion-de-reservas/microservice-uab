package com.example.microserviceuab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceUabApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceUabApplication.class, args);
    }

    /*
    @PostConstruct
    public void init() {
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
     */
}
