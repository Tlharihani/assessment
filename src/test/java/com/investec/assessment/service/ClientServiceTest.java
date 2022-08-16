package com.investec.assessment.service;

import com.investec.assessment.model.Client;
import com.investec.assessment.repository.ClientRepo;
import com.investec.assessment.service.impl.ClientServiceImpl;
import com.investec.assessment.util.IdNumberValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock

    private ClientRepo repo;

    @InjectMocks
        private ClientServiceImpl clientService;

    @Autowired
    private IdNumberValidation idNumberValidation;

    private Client client;

    @BeforeEach
    public void setup(){
       this.client = new Client();
        client.setId(1L);
        client.setFirstname("Tlharihani");
        client.setLastname("Baloyi");
        client.setIdNumber("9212205861083");
        client.setMobileNumber("0671347263");
        client.setPhysicalAddress("ebony park");
    }


}

