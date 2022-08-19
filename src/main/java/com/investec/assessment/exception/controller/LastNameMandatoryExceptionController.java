package com.investec.assessment.exception.controller;

import com.investec.assessment.exception.LastNameMandatoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LastNameMandatoryExceptionController {
    @ExceptionHandler(value = LastNameMandatoryException.class)
    public ResponseEntity<Object> exception(LastNameMandatoryException exception) {
            return new ResponseEntity<>("Last name is Mandatory", HttpStatus.BAD_REQUEST);
    }
}
