package com.arrakdique.cryptocurrencywatcher.controller.exception.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Component
public class ResponseError {
    private String message;
    private HttpStatus httpStatus;
}
