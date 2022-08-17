package com.investec.assessment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.investec.assessment.AssessmentApplication;
import com.investec.assessment.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ClientControllerTest extends AbstractTest {

    private Client client;

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
   }

   @Test
    public void createClient()  throws Exception {
       String url = "/api/create";

       String inputJson = super.mapToJson(this.client);
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
               .contentType(MediaType.APPLICATION_JSON_VALUE)
               .content(inputJson)).andReturn();

       int status = mvcResult.getResponse().getStatus();
       assertEquals(201,status);
    }

   @Test
    public void getClientById() throws Exception {


    }


}
