package com.investec.assessment.exception.controller;

import com.investec.assessment.exception.IdNumberExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MobileNumberExistException {
    @ExceptionHandler(value = IdNumberExistException.class)
    public ResponseEntity<Object> exception(IdNumberExistException exception) {
        return new ResponseEntity<>("Mobile already exist", HttpStatus.CONFLICT);
    }
}
