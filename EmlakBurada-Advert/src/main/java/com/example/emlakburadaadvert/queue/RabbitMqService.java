package com.example.emlakburadaadvert.queue;

import com.example.emlakburadaadvert.config.RabbitMqConfig;
import com.example.emlakburadaadvert.dto.request.AdvertStatusRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements QueueService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitMqConfig config;

    @Override
    public void sendMessage(AdvertStatusRequest advertStatusRequest) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), advertStatusRequest);

    }

}
