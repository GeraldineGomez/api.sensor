package com.simm.thingsboard.service.services_implementation;

import java.net.URI;
import java.net.URISyntaxException;

import com.simm.thingsboard.service.model.auth.AuthRequest;
import com.simm.thingsboard.service.model.auth.AuthResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthServiceImp {

    @Value( "${thingsboard.base-path}" )
    private String path;
    
    @Value( "${thingsboard.login.path}" )
    private String loginPath;

    public AuthResponse login(AuthRequest request) {

        AuthResponse response = new AuthResponse();

        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = path + loginPath;
        URI uri = null;
        
        try {
            uri = new URI(baseUrl);
            ResponseEntity<AuthResponse> result = restTemplate.postForEntity(uri, request, AuthResponse.class);
            response = result.getBody();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return response;

    }
    
}
