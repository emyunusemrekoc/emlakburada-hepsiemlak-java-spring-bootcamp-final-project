package com.example.emlakburadauser.service;

import com.example.emlakburadauser.dto.request.UserBalanceIdentificationRequest;

public interface RabbitMqListenerService {

    void receiveMessage(UserBalanceIdentificationRequest UserBalanceIdentificationRequest);
}
