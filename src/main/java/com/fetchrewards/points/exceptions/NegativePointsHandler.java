package com.fetchrewards.points.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NegativePointsHandler {
    @ResponseBody
    @ExceptionHandler(NegativePointsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String negativePointsHandler(NegativePointsException ex) {
        return ex.getMessage();
    }
}