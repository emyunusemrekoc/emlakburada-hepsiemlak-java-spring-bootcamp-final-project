package com.example.emlakburadaadvertstatus.service;

import com.example.emlakburadaadvertstatus.dto.request.AdvertStatusRequest;
import com.example.emlakburadaadvertstatus.model.Advert;
import com.example.emlakburadaadvertstatus.model.enums.AdvertStatus;
import com.example.emlakburadaadvertstatus.repository.AdvertDao;
import com.example.emlakburadaadvertstatus.service.impl.AdvertStatusServiceImpl;
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
public class AdvertStatusServiceTest {

    @InjectMocks
    private AdvertStatusServiceImpl advertService;

    @Mock
    private AdvertDao advertDao;

    @Mock
    private DtoConverterService dtoConverterService;


    @BeforeEach
    void setup() {


        Mockito.when(dtoConverterService.dtoToEntity(prepareAdvertStatusRequest(1), Advert.class))
                .thenReturn(prepareAdvert(1));

        Mockito
                .when(advertDao.save(any()))
                .thenReturn(prepareAdvert(1));

    }


    @Test
    void updateAdvert() {

        AdvertStatusRequest request = prepareAdvertStatusRequest(1);
        Advert advert = prepareAdvert(1);


        Result response = advertService.updateStatus(request);

        assertEquals(true, response.isSuccess());

    }


    private Advert prepareAdvert(int id) {
        return new Advert(id, AdvertStatus.IN_REVIEW);

    }

    private AdvertStatusRequest prepareAdvertStatusRequest(int id) {
        return new AdvertStatusRequest(id, AdvertStatus.IN_REVIEW);

    }


}
