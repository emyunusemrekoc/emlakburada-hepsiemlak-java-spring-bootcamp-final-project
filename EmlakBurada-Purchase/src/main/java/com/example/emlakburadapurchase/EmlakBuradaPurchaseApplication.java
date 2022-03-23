package com.example.emlakburadapurchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableEurekaClient
public class EmlakBuradaPurchaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakBuradaPurchaseApplication.class, args);
    }

}
