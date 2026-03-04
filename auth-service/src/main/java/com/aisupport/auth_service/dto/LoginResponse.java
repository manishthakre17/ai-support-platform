package com.aisupport.auth_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String token;
}