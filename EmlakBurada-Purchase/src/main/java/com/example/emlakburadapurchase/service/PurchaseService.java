package com.example.emlakburadapurchase.service;


import com.example.emlakburadapurchase.dto.request.PurchaseRequest;
import com.example.emlakburadapurchase.dto.request.PurchaseStatusRequest;
import com.example.emlakburadapurchase.dto.response.PurchaseResponse;
import com.example.emlakburadapurchase.model.enums.PurchaseStatus;
import com.example.emlakburadapurchase.utils.results.DataResult;
import com.example.emlakburadapurchase.utils.results.Result;

import java.util.List;

public interface PurchaseService {

    DataResult<List<PurchaseResponse>> findAll();
    Result purchase(PurchaseRequest purchaseRequest,int userId);
    Result updatePurchaseStatusById(int purchaseId, PurchaseStatusRequest purchaseStatusRequest);
    DataResult<PurchaseResponse> findById(int purchaseId);
    DataResult<List<PurchaseResponse>> findAllByUserId(int userId);
}
