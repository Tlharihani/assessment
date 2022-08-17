package com.investec.assessment.controller;

import com.investec.assessment.exception.ClientNotFoundException;
import com.investec.assessment.model.Client;
import com.investec.assessment.service.ClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "User Rest Controller", description = "REST API for User")
@RequestMapping("/api")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    private ResponseEntity<Client> createClient(@RequestBody Client client) {
        return new ResponseEntity<>(this.clientService.save(client), HttpStatus.CREATED);
    }

    @GetMapping("/client/id-number/{idNumber}")
    private ResponseEntity<Client> getByIdnumber(@PathVariable String idNumber) {
        return new ResponseEntity<>(this.clientService.findByIdNumber(idNumber), HttpStatus.OK);
    }

    @GetMapping("/client/mobile-number/{mobileNumber}")
    private ResponseEntity<Client> getByMobileNumber(@PathVariable String mobileNumber) {
        return new ResponseEntity<>(this.clientService.findByMobileNumber(mobileNumber), HttpStatus.OK);
    }

    @GetMapping("/client/first-name/{firstName}")
    private ResponseEntity<List<Client>> getByFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(this.clientService.findByFirstName(firstName), HttpStatus.OK);
    }


}
