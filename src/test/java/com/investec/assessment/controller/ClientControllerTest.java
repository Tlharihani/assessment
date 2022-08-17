package com.investec.assessment.controller;
import com.investec.assessment.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class ClientControllerTest extends AbstractTest {

    private Client client;

    private String createUrl = "/api/create";
    private String idNumberUrl = "/api/client/id-number/{idNumber}";
    private String mobileNumberUrl  = "/api/client/mobile-number/{mobileNumber}";
    private String firstNameUrl = "/api/client/first-name/{firstName}";




    @Override
    @Before
    public void setUp(){
       super.setUp();
       client = new Client();
       client.setId(1L);
       client.setFirstname("Tlharihani");
       client.setLastname("Baloyi");
       client.setIdNumber("9212205861083");
       client.setMobileNumber("0671347263");
       client.setPhysicalAddress("ebony park");


        try {
            String inputJson = super.mapToJson(this.client);
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.createUrl)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson)).andReturn();
        } catch (Exception e){
            throw new RuntimeException();
        }
   }

   @Test
    public void createClient()  throws Exception {
       String inputJson = super.mapToJson(this.getClient());
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.createUrl)
               .contentType(MediaType.APPLICATION_JSON_VALUE)
               .content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       assertEquals(201,status);
    }
    @Test
    public void createClientWithInvalidId()  throws Exception {

        Client client1 = this.getClient();
        client1.setIdNumber("12345678");
        String inputJson = super.mapToJson(client1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.createUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400,status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"Invalid Id Number");
    }
    @Test
    public void createClientWithDuplicateIdNumber()  throws Exception {
        String inputJson = super.mapToJson(this.client);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.createUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(409,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"Id Number already exist");
    }
    @Test
    public void createClientWithDuplicateMobileNumber()  throws Exception {
        Client client1 = this.getClient();
        client1.setMobileNumber(client.getMobileNumber());
        String inputJson = super.mapToJson(client1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.createUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(409,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"Mobile already exist");

    }

    @Test
    public void createClientWithInvalidMobileNumber()  throws Exception {
        Client client1 = getClient();
        client1.setMobileNumber("");
        String inputJson = super.mapToJson(client1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.createUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"Invalid Mobile Number");
    }

    @Test
    public void createClientWithoutIdNumber()  throws Exception {
        this.client.setIdNumber("");
        String inputJson = super.mapToJson(this.client);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.createUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"Id number is Mandatory");

    }

    @Test
    public void createClientWithoutFirstname()  throws Exception {
        Client client1 = this.getClient();
        client1.setFirstname("");
        String inputJson = super.mapToJson(client1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.createUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"First name is Mandatory");

    }

    @Test
    public void createClientWithoutLastName()  throws Exception {
        Client client1 = this.getClient();
        client1.setLastname("");
        String inputJson = super.mapToJson(client1);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(this.createUrl)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"Last name is Mandatory");

    }

   @Test
    public void getExistingClientByNonExistingIdNumber() throws Exception {
       MvcResult mvcResult = mvc.perform(get(this.idNumberUrl,"921220586108554")).andReturn();
       int status = mvcResult.getResponse().getStatus();
       assertEquals(404,status);

       String content = mvcResult.getResponse().getContentAsString();
       assertEquals(content,"Client not found");
   }

    @Test
    public void getExistingClientByFirstName() throws Exception {
        MvcResult mvcResult = mvc.perform(get(this.firstNameUrl,"Tlharihani")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }

    @Test
    public void getExistingClientByMobileNumber() throws Exception {
        MvcResult mvcResult = mvc.perform(get(this.mobileNumberUrl,"0671347263")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }@Test
    public void getExistingClientByIdNumber() throws Exception {
       MvcResult mvcResult = mvc.perform(get(this.idNumberUrl,"9212205861083")).andReturn();
       int status = mvcResult.getResponse().getStatus();
       assertEquals(200,status);
   }

    @Test
    public void getExistingClientByNonExistingFirstName() throws Exception {
        MvcResult mvcResult = mvc.perform(get(this.firstNameUrl,"Tlharidhani")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"Client not found");
    }

    @Test
    public void getExistingClientByNonExistingMobileNumber() throws Exception {
        MvcResult mvcResult = mvc.perform(get(this.mobileNumberUrl,"067134726883")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404,status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,"Client not found");
    }


    private Client getClient(){
        Client client = new Client();
        client.setId(1L);
        client.setFirstname("Tlharihani");
        client.setLastname("Baloyi");
        client.setIdNumber("9312250720083");
        client.setMobileNumber("0671346272");
        client.setPhysicalAddress("ebony park");
        return client;
    }

}
