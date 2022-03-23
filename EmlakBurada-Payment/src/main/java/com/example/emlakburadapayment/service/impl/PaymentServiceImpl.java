package com.example.emlakburadapayment.service.impl;


import com.example.emlakburadapayment.dto.request.PaymentRequest;
import com.example.emlakburadapayment.dto.response.PaymentResponse;
import com.example.emlakburadapayment.exception.AmountTypeException;
import com.example.emlakburadapayment.model.Payment;
import com.example.emlakburadapayment.repository.PaymentDao;
import com.example.emlakburadapayment.service.PaymentService;
import com.example.emlakburadapayment.utils.dtoConverter.DtoConverterService;
import com.example.emlakburadapayment.utils.results.DataResult;
import com.example.emlakburadapayment.utils.results.Result;
import com.example.emlakburadapayment.utils.results.SuccessDataResult;
import com.example.emlakburadapayment.utils.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentDao paymentDao;
    private DtoConverterService dtoConverterService;

    @Autowired
    public PaymentServiceImpl(PaymentDao paymentDao, DtoConverterService dtoConverterService) {
        this.paymentDao = paymentDao;
        this.dtoConverterService = dtoConverterService;
    }


    @Override
    public DataResult<List<PaymentResponse>> findAll() {
        return new SuccessDataResult<List<PaymentResponse>>(dtoConverterService.entityToDto(paymentDao.findAll(), PaymentResponse.class),
                "payments listed");
    }


    @Override
    public Result pay(PaymentRequest paymentRequest) {

        //purchase service de ödeme başarısız senaryosunu test etmek için.
        if(paymentRequest.getAmount()<0){
            throw new AmountTypeException("amount type error");
        }

        paymentDao.save((Payment) dtoConverterService.dtoToEntity(paymentRequest, Payment.class));
        return new SuccessResult(" payment succesfull");
    }


}
