package com.aisupport.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationRequest {
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "Invaild email formate")
	private String email;
	
	@NotBlank(message = "password is required")
	@Size(min = 4, message = "Password must be at least 4 characters")
	private String password;
	
}
