package com.example.emlakburadaadvert.service;

import com.example.emlakburadaadvert.dto.request.AdvertRequest;
import com.example.emlakburadaadvert.dto.request.AdvertStatusRequest;
import com.example.emlakburadaadvert.dto.request.AdvertUpdateRequest;
import com.example.emlakburadaadvert.dto.response.AdvertResponse;
import com.example.emlakburadaadvert.model.enums.AdvertStatus;
import com.example.emlakburadaadvert.utils.results.DataResult;
import com.example.emlakburadaadvert.utils.results.Result;

import java.util.List;

public interface AdvertService {
    DataResult<List<AdvertResponse>> findAll();
    Result add(AdvertRequest advertRequest,int id);
    Result updateById(int advertId, AdvertUpdateRequest advertUpdateRequest);
    Result deleteById(int advertId);
    DataResult<AdvertResponse> findById(int advertId);
    DataResult<List<AdvertResponse>> findAllByUserIdAndIsActive(int userId);
    DataResult<List<AdvertResponse>> findAllByUserIdAndIsPassive(int userId);
    Result updateStatusActiveById(int advertId);
    Result updateStatusPassiveById(int advertId);

}
