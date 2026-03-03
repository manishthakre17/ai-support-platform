package com.aisupport.auth_service.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.aisupport.auth_service.dto.UserRegistrationRequest;
import com.aisupport.auth_service.entity.User;
import com.aisupport.auth_service.exception.EmailAlreadyExistsException;
import com.aisupport.auth_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.aisupport.auth_service.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	@Mock
	private UserRepository userRepository;
	@Mock
	private PasswordEncoder passwordEncoder;
	@InjectMocks
	private UserServiceImpl userService;
	@Test
	public void shouldRegisterUserSucessfully() {
		 UserRegistrationRequest request = UserRegistrationRequest.builder()
	                .name("Manish")
	                .email("manish@test.com")
	                .password("password123")
	                .build();
		 
		 when(userRepository.findByEmail(request.getEmail()))
		 .thenReturn(Optional.empty());
		 
		 when(passwordEncoder.encode(request.getPassword()))
		 .thenReturn("Password Encoded");
		 
		 
		 userService.registerUser(request);
		 
		 verify(userRepository, times(1)).save(any(User.class));
		 
	}
	
	 @Test
	    void shouldThrowExceptionWhenEmailAlreadyExists() {

	        // Given
	        UserRegistrationRequest request = UserRegistrationRequest.builder()
	                .name("Manish")
	                .email("manish@test.com")
	                .password("password123")
	                .build();

	        when(userRepository.findByEmail(request.getEmail()))
	                .thenReturn(Optional.of(new User()));

	        // When & Then
	        assertThatThrownBy(() -> userService.registerUser(request))
	                .isInstanceOf(EmailAlreadyExistsException.class);
	    }

}
