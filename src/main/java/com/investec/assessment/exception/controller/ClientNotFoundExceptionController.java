package com.investec.assessment.exception.controller;

import com.investec.assessment.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientNotFoundExceptionController {
    @ExceptionHandler(value = ClientNotFoundException.class)
    public ResponseEntity<Object> exception(ClientNotFoundException exception) {
        return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
    }
}
