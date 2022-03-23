package com.example.emlakburadaauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmlakBuradaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakBuradaAuthApplication.class, args);
    }

}
