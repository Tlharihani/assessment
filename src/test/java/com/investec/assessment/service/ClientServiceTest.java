package com.investec.assessment.service;

import com.investec.assessment.model.Client;
import com.investec.assessment.repository.ClientRepo;
import com.investec.assessment.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepo repo;


    @InjectMocks
    private ClientServiceImpl clientService;


    private Client client;

    @BeforeEach
    public void setup() {
        this.client = new Client();
        client.setId(1L);
        client.setFirstname("Tlharihani");
        client.setLastname("Baloyi");
        client.setIdNumber("9212205861083");
        client.setMobileNumber("0671347263");
        client.setPhysicalAddress("ebony park");
    }

    @DisplayName("JUnit test for Save client")
    @Test
    public void saveClient() throws Exception {
        when(repo.save(client)).thenReturn(client);
        Client savedClient = repo.save(client);
        assert(savedClient.getIdNumber()).equals(client.getIdNumber());
    }

    @DisplayName("JUnit test for get client by first name ")
    @Test
    public void getClientsByFirstName() throws Exception {
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        when(repo.findByFirstname(client.getFirstname()))
                .thenReturn(Optional.of(clientList));
        List<Client> client1 = clientService.findByFirstName(client.getFirstname()
        );
        assertThat(client1).isNotNull();

    }

    @DisplayName("JUnit test for get client by id number ")
    @Test
    public void getClientByIdNumber() throws Exception {
        when(repo.findByIdNumber(client.getIdNumber()))
                .thenReturn(Optional.of(client));
        Client client1 = clientService.findByIdNumber(client.getIdNumber());
        assertThat(client1).isNotNull();

    }

    @DisplayName("JUnit test for get client by mobile Number ")
    @Test
    public void getClientByMobileNumber() throws Exception {
        when(repo.findByMobileNumber(client.getMobileNumber()))
                .thenReturn(Optional.of(client));
        Client client1 = clientService.findByMobileNumber(client.getMobileNumber());
        assertThat(client1).isNotNull();

    }
}

