package com.investec.assessment.exception.controller;

import com.investec.assessment.exception.FirstNameMandatoryException;
import com.investec.assessment.exception.IdNumberMandatoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class firstNameMandatoryExceptionController {
    @ExceptionHandler(value = FirstNameMandatoryException.class)
    public ResponseEntity<Object> exception(FirstNameMandatoryException exception) {
            return new ResponseEntity<>("First name is Mandatory", HttpStatus.BAD_REQUEST);
    }
}
