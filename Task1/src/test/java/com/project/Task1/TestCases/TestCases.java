package com.project.Task1.TestCases;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Task1.Customer.Model.LoginDTO;
import com.project.Task1.Task1Application;
import com.project.Task1.Task1ApplicationTests;
import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
public class TestCases extends Task1ApplicationTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;
    private String jsonRequest;
    private LoginDTO loginDTO;


    @Before
    public void setup() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        loginDTO = new LoginDTO("ssamha@gmail.com","12345678");
        jsonRequest = mapper.writeValueAsString(loginDTO);
    }

    @Test
    public void testEmployee() throws Exception {
        mockMvc.perform(post("/normal-login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.first_name").value("samhasssn"))
                .andExpect(jsonPath("$.last_name").value("ahd"));

    }



}
