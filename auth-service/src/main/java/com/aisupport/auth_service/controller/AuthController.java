package com.aisupport.auth_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aisupport.auth_service.dto.LoginRequest;
import com.aisupport.auth_service.dto.LoginResponse;
import com.aisupport.auth_service.dto.UserRegistrationRequest;
import com.aisupport.auth_service.security.JwtService;
import com.aisupport.auth_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	
	private final JwtService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(
			@Valid @RequestBody UserRegistrationRequest userRegistrationRequest){
		userService.registerUser(userRegistrationRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	}
	
	
	 // Login API
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestBody LoginRequest request) {

//        userService.loginUser(request);

        String token = jwtService.generateToken(request.getEmail());

        return ResponseEntity.ok(
                new LoginResponse(token, "Bearer", request.getEmail())
        );
    }
	
}
