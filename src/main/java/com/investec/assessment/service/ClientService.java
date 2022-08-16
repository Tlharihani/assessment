package com.investec.assessment.service;

import com.investec.assessment.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    public Client save(Client client);
    Client findByIdNumber(String idNumber);
    Client findByMobileNumber(String mobileNumber);
    List<Client> findByFirstName(String firstName);
}
