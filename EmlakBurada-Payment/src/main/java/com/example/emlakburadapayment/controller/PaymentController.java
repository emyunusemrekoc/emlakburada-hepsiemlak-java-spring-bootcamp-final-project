package com.example.emlakburadapayment.controller;

import com.example.emlakburadapayment.dto.request.PaymentRequest;
import com.example.emlakburadapayment.dto.response.PaymentResponse;
import com.example.emlakburadapayment.service.PaymentService;
import com.example.emlakburadapayment.utils.results.DataResult;
import com.example.emlakburadapayment.utils.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {


    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // ödeme servisi
    @GetMapping("/payments")
    public ResponseEntity<DataResult<List<PaymentResponse>>> findAll() {
        DataResult<List<PaymentResponse>> result= paymentService.findAll();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/payments")
    public ResponseEntity<Result> pay(@RequestBody PaymentRequest paymentRequest) {
        Result result = paymentService.pay(paymentRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }


}
