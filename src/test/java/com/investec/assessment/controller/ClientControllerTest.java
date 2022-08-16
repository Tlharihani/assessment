package com.investec.assessment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClientControllerTest {

    @MockBean
    private ClientController clientController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(clientController).isNotNull();
    }
}
