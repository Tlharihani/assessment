package com.investec.assessment.exception.controller;

import com.investec.assessment.exception.IdNumberExistException;
import com.investec.assessment.exception.MobileNumberExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MobileNumberExistExceptionController {
    @ExceptionHandler(value = MobileNumberExistException.class)
    public ResponseEntity<Object> exception(MobileNumberExistException exception) {
        return new ResponseEntity<>("Mobile already exist", HttpStatus.CONFLICT);
    }
}
