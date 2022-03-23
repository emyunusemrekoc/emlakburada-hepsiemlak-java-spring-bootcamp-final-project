package com.example.emlakburadaadvert.client;

import com.example.emlakburadaadvert.client.request.UserBalanceRequest;
import com.example.emlakburadaadvert.client.response.IndividualUserResponse;
import com.example.emlakburadaadvert.client.response.UserBalanceResponse;
import com.example.emlakburadaadvert.utils.results.DataResult;
import com.example.emlakburadaadvert.utils.results.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "feign-advert-to-individual-user", url = "http://localhost:8080")
public interface UserClient {


    @GetMapping(value = "/individual-users/{id}")
    DataResult<IndividualUserResponse> findById(@PathVariable int id);

    @GetMapping("/individual-users/{id}/find-balance")
    DataResult<UserBalanceResponse> findByIdForBalance(@PathVariable int id);

    @PutMapping("/individual-users/{id}/update-balance")
    Result updateBalanceById(@PathVariable int id, @RequestBody UserBalanceRequest userBalanceRequest);

}
