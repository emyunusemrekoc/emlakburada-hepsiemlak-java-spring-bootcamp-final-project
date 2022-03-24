package com.example.emlakburadaadvertstatus.service;

import com.example.emlakburadaadvertstatus.dto.request.AdvertStatusRequest;
import com.example.emlakburadaadvertstatus.model.Advert;
import com.example.emlakburadaadvertstatus.model.enums.AdvertStatus;
import com.example.emlakburadaadvertstatus.repository.AdvertDao;
import com.example.emlakburadaadvertstatus.service.impl.AdvertStatusServiceImpl;
import com.example.emlakburadaadvertstatus.service.impl.RabbitMqListenerServiceImpl;
import com.example.emlakburadaadvertstatus.utils.dtoConverter.DtoConverterService;
import com.example.emlakburadaadvertstatus.utils.results.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class RabbitMqListenerServiceTest {
    @InjectMocks
    private RabbitMqListenerServiceImpl rabbitMqListenerService;

    @Mock
    private AdvertStatusServiceImpl advertStatusService;






    @Test
    void updateAdvert() {

        AdvertStatusRequest request = prepareAdvertStatusRequest(1);



       rabbitMqListenerService .receiveMessage(request);
       Mockito.verify(advertStatusService).updateStatus(any());


    }


    private Advert prepareAdvert(int id) {
        return new Advert(id, AdvertStatus.IN_REVIEW);

    }

    private AdvertStatusRequest prepareAdvertStatusRequest(int id) {
        return new AdvertStatusRequest(id, AdvertStatus.IN_REVIEW);

    }

}
