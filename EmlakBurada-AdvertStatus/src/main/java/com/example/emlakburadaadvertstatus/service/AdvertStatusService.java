package com.example.emlakburadaadvertstatus.service;

import com.example.emlakburadaadvertstatus.dto.request.AdvertStatusRequest;
import com.example.emlakburadaadvertstatus.utils.results.Result;

public interface AdvertStatusService {
    Result updateStatus(AdvertStatusRequest advertRequest);
}
