package com.aisupport.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginRequest {
	
	@NotBlank(message = "Email can not be blank")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Password can not be blank")
	private String password;

}
