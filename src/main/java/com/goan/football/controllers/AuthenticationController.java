package com.goan.football.controllers;

import com.goan.football.auth.AuthenticationRequest;
import com.goan.football.auth.AuthenticationResponse;
import com.goan.football.auth.AuthenticationService;
import com.goan.football.auth.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
@AllArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    @MutationMapping
    public AuthenticationResponse registerUser(@Argument RegisterRequest register) {
        return service.register(register);
    }

    @MutationMapping
    public AuthenticationResponse authenticate(@Argument AuthenticationRequest authentication) {
        return service.authenticate(authentication);
    }

    @QueryMapping
    public boolean tokenIsValid(@Argument String token){
        return service.tokenIsValid(token);
    }

    @MutationMapping
    public Boolean logout(@Argument String token) {
        service.logout(token);
        return true;
    }

}