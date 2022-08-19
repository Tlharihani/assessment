package com.investec.assessment.service.impl;

import com.investec.assessment.exception.*;
import com.investec.assessment.model.Client;
import com.investec.assessment.repository.ClientRepo;
import com.investec.assessment.service.ClientService;
import com.investec.assessment.util.IdNumberValidation;
import com.investec.assessment.util.MobileNumberValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.investec.assessment.util.StaticInfo.UPDATE_STATE;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private IdNumberValidation idNumberValidation;

    @Autowired
    private MobileNumberValidation mobileNumberValidation;

    @Override
    public Client save(String state, Client client) {
        try {
            if (this.validateMandatoryField(client) && this.validateIdNumber(client.getIdNumber())
                    && this.validateMobileNumber(client.getMobileNumber())
            && this.checkExistingContactNumber(state,client.getMobileNumber())
            && this.checkExistingIdNumber(state, client.getIdNumber())) {
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
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new ClientNotFoundException();
        }
    }

    @Override
    public Client findByMobileNumber(String mobileNumber) {
        Optional<Client> client = this.clientRepo.findByMobileNumber(mobileNumber);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new ClientNotFoundException();
        }
    }

    @Override
    public List<Client> findByFirstName(String firstName) {
        Optional<List<Client>> clients = this.clientRepo.findByFirstname(firstName);
        if (clients.isPresent() && !clients.get().isEmpty()) {
            return clients.get();
        } else {
            throw new ClientNotFoundException();
        }
    }

    @Override
    public Client updateClient(Client client) {
        try {
            if (this.validateMandatoryField(client) && this.validateIdNumber(client.getIdNumber())
                    && this.validateMobileNumber(client.getMobileNumber())) {
                return this.clientRepo.save(client);
            } else {
                throw new BadRequestException();
            }
        } catch (Exception exception) {
            throw exception;
        }
    }

    private boolean validateIdNumber(String idNumber) {
        if (idNumber.isEmpty()) {
            throw new IdNumberMandatoryException();
        } else if (this.idNumberValidation.isValidIdNumber(idNumber)) {
            return true;
        } else {
            throw new InvalidIdNumberException();
        }

    }

    private boolean validateMobileNumber(String mobileNumber) {
        if (this.mobileNumberValidation.validateMobileNumber(mobileNumber)) {
            return true;
        } else {
            throw new InvalidMobileNumberException();
        }
    }

    private boolean validateMandatoryField(Client client) {
        if (client.getFirstname().isEmpty()) {
            throw new FirstNameMandatoryException();
        } else if (client.getLastname().isEmpty()) {
            throw new LastNameMandatoryException();
        } else {
            return true;
        }
    }

    private boolean checkExistingIdNumber(String state, String idNumber) {
        Optional<Client> client1 = this.clientRepo.findByIdNumber(idNumber);
        if (client1.isPresent()) {
            if (state.equalsIgnoreCase(UPDATE_STATE)) {
                if (client1.get().getIdNumber().equalsIgnoreCase(idNumber)) {
                    return true;
                } else {
                    throw new IdNumberExistException();
                }
            } else {
                throw new IdNumberExistException();
            }
        } else {
            return true;
        }
    }

    private boolean checkExistingContactNumber(String state, String mobileNumber) {
        Optional<Client> client1 = this.clientRepo.findByMobileNumber(mobileNumber);
        if (client1.isPresent()) {
            if (state.equalsIgnoreCase(UPDATE_STATE)) {
                if (client1.get().getMobileNumber().equalsIgnoreCase(mobileNumber)) {
                    return true;
                } else {
                    throw new MobileNumberExistException();
                }
            } else {
                throw new MobileNumberExistException();
            }
        } else {
            return true;
        }
    }
}
