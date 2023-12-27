package com.restapi.exceptions;

import com.restapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(RMValidateException.class)
    public ResponseEntity<ErrorResponse> handleMenuValidationException(RMValidateException ex) {
        return new ResponseEntity<>(ex.getErrorResponse(), HttpStatus.valueOf(ex.getErrorResponse().getCode()));
    }
}