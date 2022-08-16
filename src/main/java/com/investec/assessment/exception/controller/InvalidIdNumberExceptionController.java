package com.investec.assessment.exception.controller;

import com.investec.assessment.exception.InvalidIdNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidIdNumberExceptionController {
    @ExceptionHandler(value = InvalidIdNumberException.class)
    public ResponseEntity<Object> exception(InvalidIdNumberException exception) {
        return new ResponseEntity<>("Invalid Id Number", HttpStatus.BAD_REQUEST);
    }
}
