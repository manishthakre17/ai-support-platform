package com.aisupport.auth_service.service;

import com.aisupport.auth_service.dto.UserRegistrationRequest;

public interface UserService {
	void registerUser(UserRegistrationRequest request);
}
