package com.example.emlakburadapayment.service;


import com.example.emlakburadapayment.dto.request.PaymentRequest;
import com.example.emlakburadapayment.dto.response.PaymentResponse;
import com.example.emlakburadapayment.utils.results.DataResult;
import com.example.emlakburadapayment.utils.results.Result;

import java.util.List;

public interface PaymentService {

    DataResult<List<PaymentResponse>> findAll();
    Result pay(PaymentRequest paymentRequest);

}
