package com.investec.assessment.exception.controller;

import com.investec.assessment.exception.InvalidMobileNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidMobileNumberController {
    @ExceptionHandler(value = InvalidMobileNumberException.class)
    public ResponseEntity<Object> exception(InvalidMobileNumberException exception) {
        return new ResponseEntity<>("Invalid Mobile Number", HttpStatus.BAD_REQUEST);
    }
}
