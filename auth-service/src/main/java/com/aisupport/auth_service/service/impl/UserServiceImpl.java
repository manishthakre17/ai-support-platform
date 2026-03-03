package com.aisupport.auth_service.service.impl;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aisupport.auth_service.dto.UserRegistrationRequest;
import com.aisupport.auth_service.entity.User;
import com.aisupport.auth_service.exception.EmailAlreadyExistsException;
import com.aisupport.auth_service.repository.UserRepository;
import com.aisupport.auth_service.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
	@Override
	public void registerUser(UserRegistrationRequest request) {
		if(userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException(request.getEmail()+" Email Already exist");
		}
		User user = User.builder()
				.name(request.getName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.build();
		
		userRepository.save(user);
		
		
		
		
	}

}
