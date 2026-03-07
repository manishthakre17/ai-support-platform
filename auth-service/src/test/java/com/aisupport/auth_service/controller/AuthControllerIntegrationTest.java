package com.aisupport.auth_service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {

        String requestBody = """
                {
                  "name": "Manish",
                  "email": "manish@test.com",
                  "password": "password123"
                }
                """;

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }
    
    @Test
    void shouldUserLogin() throws Exception{
    	String body = """
    		{
    				"name" : "Manish",
    				"email":"manish@gmail.com",
    				"password" : "12345"
    		}""";
    	 mockMvc.perform(post("/api/auth/login")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(body))
                 .andExpect(status().isOk())
                 .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath("$.token").exists());;
    	
    }

    @Test
    void shouldReturnBadRequestForInvalidData() throws Exception {

        String requestBody = """
                {
                  "name": "",
                  "email": "invalid",
                  "password": ""
                }
                """;

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
