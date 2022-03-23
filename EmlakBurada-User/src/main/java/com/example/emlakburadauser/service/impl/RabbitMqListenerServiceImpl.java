package com.example.emlakburadauser.service.impl;


import com.example.emlakburadauser.dto.request.UserBalanceIdentificationRequest;
import com.example.emlakburadauser.service.IndividiualUserService;
import com.example.emlakburadauser.service.RabbitMqListenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqListenerServiceImpl implements RabbitMqListenerService {


    private IndividiualUserService individiualUserService;

    @Autowired
    public RabbitMqListenerServiceImpl(IndividiualUserService individiualUserService) {
        this.individiualUserService = individiualUserService;
    }


    @RabbitListener(queues = "${emlakburada.rabbitmq.queue}")
    public void receiveMessage(UserBalanceIdentificationRequest userBalanceIdentificationRequest) {
        log.info(userBalanceIdentificationRequest.toString());
        individiualUserService.updateBalanceByRabbitMq(userBalanceIdentificationRequest.getUserId(), userBalanceIdentificationRequest);
    }

}
