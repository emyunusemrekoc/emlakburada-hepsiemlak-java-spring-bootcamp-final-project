package com.example.emlakburadauser.service;

import com.example.emlakburadauser.dto.request.IndividualUserRequest;
import com.example.emlakburadauser.dto.request.UserBalanceIdentificationRequest;
import com.example.emlakburadauser.dto.request.UserBalanceRequest;
import com.example.emlakburadauser.dto.response.IndividualUserResponse;
import com.example.emlakburadauser.dto.response.UserBalanceResponse;
import com.example.emlakburadauser.utils.results.DataResult;
import com.example.emlakburadauser.utils.results.Result;

import java.util.List;

public interface IndividiualUserService {
    DataResult<List<IndividualUserResponse>> findAll();
    Result add(IndividualUserRequest individualUserRequest);
    Result updateById(int individualUserId,IndividualUserRequest individualUserRequest);
    Result deleteById(int individualUserId);
    DataResult<IndividualUserResponse> findById(int individualUserId);
    Result updateBalanceById(int individualUserId, UserBalanceRequest userBalanceRequest);
    Result updateBalanceByRabbitMq(int individualUserId, UserBalanceIdentificationRequest userBalanceIdentificationRequest);
    DataResult<UserBalanceResponse> findByIdForBalance(int individualUserId);
}
