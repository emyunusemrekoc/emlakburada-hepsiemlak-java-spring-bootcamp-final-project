package com.example.emlakburadaadvertstatus.service;

import com.example.emlakburadaadvertstatus.dto.request.AdvertStatusRequest;

public interface RabbitMqListenerService {

    void receiveMessage(AdvertStatusRequest advertStatusRequest);
}
