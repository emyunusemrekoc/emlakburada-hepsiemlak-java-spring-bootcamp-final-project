package com.example.emlakburadaadvert.advice;

import com.example.emlakburadaadvert.exception.AdvertNotFoundException;
import com.example.emlakburadaadvert.utils.results.ErrorDataResult;
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

    @ExceptionHandler(AdvertNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorDataResult<Object> handleAdvertNotFoundException(AdvertNotFoundException advertNotFoundException) {
        log.error("advert not found || " + advertNotFoundException.getMessage());
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>("advert not found");
        return errorDataResult;
    }


}
