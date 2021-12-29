package com.simm.thingsboard.service.services;

import com.simm.thingsboard.service.model.auth.AuthRequest;
import com.simm.thingsboard.service.model.auth.AuthResponse;
import com.simm.thingsboard.service.services_implementation.AuthServiceImp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@RequestMapping("auth")
public class AuthService {

    @Autowired
    AuthServiceImp authServiceImp;
    
    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthResponse login(@RequestBody AuthRequest request) {

        return authServiceImp.login(request);

    }

}
