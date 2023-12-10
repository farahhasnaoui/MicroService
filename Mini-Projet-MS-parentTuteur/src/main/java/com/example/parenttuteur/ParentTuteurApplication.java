package com.example.parenttuteur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ParentTuteurApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParentTuteurApplication.class, args);
    }

}
