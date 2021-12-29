package com.simm.thingsboard.service.model.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    
    private String token;

    private String refreshToken;

}
