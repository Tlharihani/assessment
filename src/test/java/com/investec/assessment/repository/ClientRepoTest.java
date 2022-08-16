package com.investec.assessment.repository;

import com.investec.assessment.AssessmentApplication;
import com.investec.assessment.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = AssessmentApplication.class)
public class ClientRepoTest {

    @Autowired
    private ClientRepo clientRepo;

    private Client client;

    @BeforeEach
    public void setup(){
        client = new Client();
        client.setId(1L);
        client.setFirstname("Tlharihani");
        client.setLastname("Baloyi");
        client.setIdNumber("9212245851087");
        client.setMobileNumber("0671347263");
        client.setPhysicalAddress("ebony park");
        this.clientRepo.save(client);

    }

    @Test
    public void testSave() {
        Client client = this.getClient();
        this.clientRepo.save(client);
    }
    @Test
    public void testFindAll() {
        List<Client> result = new ArrayList<>();
        clientRepo.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 1);
    }
    @Test
    public void testFindIdNumber() {
        Client client = this.getClient();
        this.clientRepo.save(client);
        Optional<Client> client1 = clientRepo.findByIdNumber(client.getIdNumber());
        assertEquals(client1.isPresent(), true);
    }

    @Test
    public void findByMobileNumber() {
        Client client = this.getClient();
        this.clientRepo.save(client);
        Optional<Client> client1 = clientRepo.findByMobileNumber(client.getMobileNumber());
        assertEquals(client1.isPresent(), true);
    }


    @Test
    public void findByFirstName() {
        List<Client> clients = this.getClients();
       this.clientRepo.saveAll(clients);
        Optional<List<Client>> clients1 = clientRepo.findByFirstname("Tlharihani");
        if(clients1.isPresent()){
            assertEquals(clients1.get().size() > 0, true);
        }
    }
    private Client getClient(){
        Client client = new Client();
        client.setId(1L);
        client.setFirstname("Tlharihani");
        client.setLastname("Baloyi");
        client.setIdNumber("9212205682181");
        client.setMobileNumber("0671347263");
        client.setPhysicalAddress("ebony park");
        return client;
    }

    private List<Client> getClients(){
        Client client = new Client();
        client.setId(1L);
        client.setFirstname("Tlharihani");
        client.setLastname("Baloyi");
        client.setIdNumber("9212205682181");
        client.setMobileNumber("0671347263");
        client.setPhysicalAddress("ebony park");

        Client client1 = new Client();
        client1.setId(3L);
        client1.setFirstname("Tlharihani");
        client1.setLastname("Baloyi");
        client1.setIdNumber("9212205682181");
        client1.setMobileNumber("0671347263");
        client1.setPhysicalAddress("ebony park");

        Client client2 = new Client();
        client2.setFirstname("Tlhari");
        client2.setLastname("Baloyi");
        client2.setIdNumber("9212205682181");
        client2.setMobileNumber("0671347263");
        client2.setPhysicalAddress("ebony park");
        List<Client> list = new ArrayList<>();
        list.add(client);
        list.add(client2);
        list.add(client1);
        return list;
    }
}
