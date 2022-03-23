package com.example.emlakburadaadvertstatus.service.impl;

import com.example.emlakburadaadvertstatus.client.AdvertClient;
import com.example.emlakburadaadvertstatus.dto.request.AdvertStatusRequest;
import com.example.emlakburadaadvertstatus.model.Advert;
import com.example.emlakburadaadvertstatus.repository.AdvertDao;
import com.example.emlakburadaadvertstatus.service.AdvertStatusService;
import com.example.emlakburadaadvertstatus.utils.dtoConverter.DtoConverterService;
import com.example.emlakburadaadvertstatus.utils.results.Result;
import com.example.emlakburadaadvertstatus.utils.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertStatusServiceImpl implements AdvertStatusService {

    private AdvertDao advertDao;
    private DtoConverterService dtoConverterService;
    private AdvertClient advertClient;

    @Autowired
    public AdvertStatusServiceImpl(AdvertDao advertDao, DtoConverterService dtoConverterService, AdvertClient advertClient) {
        this.advertDao = advertDao;
        this.dtoConverterService = dtoConverterService;
        this.advertClient = advertClient;
    }

    //queue dan gelen ilan id ve status bilgisine göre ilan statüsü güncelleme
    @Override
    public Result updateStatus(AdvertStatusRequest advertRequest) {
        // exceptionlara bak
        Advert advert = (Advert) dtoConverterService.dtoToEntity(advertRequest, Advert.class);
        advertDao.save(advert);
        return new SuccessResult("status updated");
    }
}
