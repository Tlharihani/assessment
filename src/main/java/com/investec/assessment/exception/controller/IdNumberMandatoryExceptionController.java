package com.investec.assessment.exception.controller;

import com.investec.assessment.exception.IdNumberMandatoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IdNumberMandatoryExceptionController {
    @ExceptionHandler(value = IdNumberMandatoryException.class)
    public ResponseEntity<Object> exception(IdNumberMandatoryException exception) {
            return new ResponseEntity<>("Id number is Mandatory", HttpStatus.BAD_REQUEST);
    }
}
