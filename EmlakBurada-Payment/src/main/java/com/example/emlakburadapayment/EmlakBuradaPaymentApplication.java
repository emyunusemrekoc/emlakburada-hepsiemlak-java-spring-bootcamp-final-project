package com.example.emlakburadapayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmlakBuradaPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmlakBuradaPaymentApplication.class, args);
    }

}
