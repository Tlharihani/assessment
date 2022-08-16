package com.investec.assessment.service.impl;

import com.investec.assessment.exception.*;
import com.investec.assessment.model.Client;
import com.investec.assessment.repository.ClientRepo;
import com.investec.assessment.service.ClientService;
import com.investec.assessment.util.IdNumberValidation;
import com.investec.assessment.util.MobileNumberValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private IdNumberValidation idNumberValidation;

    @Autowired
    private MobileNumberValidation mobileNumberValidation;

    @Override
    public Client save(Client client) {
        try {
            if( this.validateMandatoryField(client) && this.validateIdNumber(client.getIdNumber()) && this.validateMobileNumber(client.getMobileNumber())) {
                return this.clientRepo.save(client);
                } else {
                throw new BadRequestException();
            }
        } catch (Exception exception) {
            throw exception;
        }
    }
    @Override
    public Client findByIdNumber(String idNumber) {
        Optional<Client> client = this.clientRepo.findByIdNumber(idNumber);
        if(client.isPresent()){
            return client.get();
        } else{
            throw new ClientNotFoundException();
        }
    }
    @Override
    public Client findByMobileNumber(String mobileNumber) {
        Optional<Client> client =  this.clientRepo.findByMobileNumber(mobileNumber);
        if(client.isPresent()){
            return client.get();
        } else{
            throw new ClientNotFoundException();
        }
    }

    @Override
    public List<Client> findByFirstName(String firstName) {
        Optional<List<Client>> clients = this.clientRepo.findByFirstname(firstName);
        if (clients.isPresent() && !clients.get().isEmpty()){
            return clients.get();
        } else{
            throw new ClientNotFoundException();
        }
    }

    private boolean validateIdNumber(String idNumber) {
        if (idNumber.isEmpty()) {
            throw new IdNumberMandatoryException();
        } else {


            if (this.idNumberValidation.isValidIdNumber(idNumber)) {
                if (this.clientRepo.findByIdNumber(idNumber).isPresent()) {
                    throw new IdNumberExistException();
                } else {
                    return true;
                }

            } else {
                throw new InvalidIdNumberException();
            }
        }
        }
        private boolean validateMobileNumber (String mobileNumber){
            if (this.mobileNumberValidation.validateMobileNumber(mobileNumber)) {
                if (this.clientRepo.findByMobileNumber(mobileNumber).isPresent()) {
                    throw new MobileNumberExistException();
                } else {
                    return true;
                }
            } else {
                throw new InvalidMobileNumberException();
            }
        }

    private boolean validateMandatoryField(Client client){
        if(client.getFirstname().isEmpty()){
            throw new FirstNameMandatoryException();
        } else if(client.getLastname().isEmpty()){
            throw new LastNameMandatoryException();
        } else{
            return true;
        }
    }
}
