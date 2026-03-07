package com.aisupport.auth_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.aisupport.auth_service.AuthServiceApplication;
import com.aisupport.auth_service.security.JwtService;

import lombok.RequiredArgsConstructor;

@SpringBootTest(classes = AuthServiceApplication.class)
public class JwtServiceTest {
	
	 @Autowired
	 private JwtService jwtService;

	 @Test
	 void shouldGenerateToken() throws Exception{
		String token = jwtService.generateToken("manish@gmail.com");
		 assertNotNull(token);
		 
	 }
	 
	 @Test
	 void shoudValidedToken() throws Exception{
		 String token = jwtService.generateToken("manish@gmail.com");
		 String username = jwtService.extractUsername(token);
		 assertEquals(username, "manish@gmail.com");
	 }

}
