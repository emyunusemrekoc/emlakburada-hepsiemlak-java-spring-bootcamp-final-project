package com.example.emlakburadapurchase.queue;

import com.example.emlakburadapurchase.dto.request.UserBalanceIdentificationRequest;

public interface QueueService {

    void sendMessage(UserBalanceIdentificationRequest userBalanceIdentificationRequest);

}
