package com.example.emlakburadapayment.advice;


import com.example.emlakburadapayment.exception.AmountTypeException;
import com.example.emlakburadapayment.utils.results.ErrorDataResult;
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

    @ExceptionHandler(AmountTypeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleFeignException(AmountTypeException amountTypeException) {
        log.error("amount type is negative number|| " + amountTypeException.getMessage());
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>("amount type is negative number");
        return errorDataResult;
    }


}
