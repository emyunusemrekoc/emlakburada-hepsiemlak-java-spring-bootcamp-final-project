package com.example.emlakburadapurchase.advice;


import com.example.emlakburadapurchase.exception.PaymentFailedException;
import com.example.emlakburadapurchase.exception.PurchaseNotFoundException;
import com.example.emlakburadapurchase.utils.results.ErrorDataResult;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleFeignException(FeignException feignException) {
        log.error("feign client error || " + feignException.getMessage());
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>("feign client error");
        return errorDataResult;
    }

    @ExceptionHandler(PaymentFailedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handlePaymentFailedException(PaymentFailedException paymentFailedException) {
        log.error("payment error || " + paymentFailedException.getMessage());
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>("payment error");
        return errorDataResult;
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handlePurchaseNotFoundException(PurchaseNotFoundException purchaseNotFoundException) {
        log.error("purchase not found|| " + purchaseNotFoundException.getMessage());
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>("purchase not found");
        return errorDataResult;
    }


}
