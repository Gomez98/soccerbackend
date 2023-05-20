package com.goan.football.controllers;

import com.goan.football.auth.AuthenticationRequest;
import com.goan.football.auth.AuthenticationResponse;
import com.goan.football.auth.AuthenticationService;
import com.goan.football.auth.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@AllArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    @QueryMapping
    public AuthenticationResponse registerUser(@Argument RegisterRequest register) {
        return service.register(register);
    }
    @QueryMapping
    public AuthenticationResponse authenticate(@Argument AuthenticationRequest authentication) {
        return service.authenticate(authentication);
    }

    @QueryMapping
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}