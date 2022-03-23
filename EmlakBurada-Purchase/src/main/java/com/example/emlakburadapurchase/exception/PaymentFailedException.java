package com.example.emlakburadapurchase.exception;

public class PaymentFailedException extends RuntimeException{

    public PaymentFailedException(String message) {
        super(message);
    }
}
