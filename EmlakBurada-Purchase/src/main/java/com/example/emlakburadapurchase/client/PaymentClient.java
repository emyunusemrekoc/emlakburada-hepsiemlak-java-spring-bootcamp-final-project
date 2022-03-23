package com.example.emlakburadapurchase.client;


import com.example.emlakburadapurchase.client.request.PaymentRequest;
import com.example.emlakburadapurchase.utils.results.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "feign-purchase-to-payment", url = "http://localhost:8084")
public interface PaymentClient {


    @PostMapping("/payments")
    Result pay(@RequestBody PaymentRequest paymentRequest);


}
