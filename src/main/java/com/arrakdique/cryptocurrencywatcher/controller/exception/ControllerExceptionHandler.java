package com.arrakdique.cryptocurrencywatcher.controller.exception;

import com.arrakdique.cryptocurrencywatcher.controller.exception.error.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseError> handleCoinNotFound() {
        return new ResponseEntity<>(ResponseError.builder()
                .message("incorrect symbol in request")
                .httpStatus(HttpStatus.NOT_FOUND)
                .build(), HttpStatus.NOT_FOUND);
    }

}
