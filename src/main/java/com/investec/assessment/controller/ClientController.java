package com.investec.assessment.controller;

import com.investec.assessment.model.Client;
import com.investec.assessment.service.ClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.investec.assessment.util.StaticInfo.SAVE_STATE;
import static com.investec.assessment.util.StaticInfo.UPDATE_STATE;

@Api(value = "User Rest Controller", description = "REST API for User")
@RequestMapping("/api")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/client/create")
    private ResponseEntity<Client> createClient(@RequestBody Client client) {
        return new ResponseEntity<>(this.clientService.save(SAVE_STATE,client), HttpStatus.CREATED);
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

    @PutMapping("/client/update")
    private ResponseEntity<Client> updateClient(@RequestBody Client client) {
        return new ResponseEntity<>(this.clientService.save(UPDATE_STATE,client), HttpStatus.OK);
    }


}
