package com.goan.football.controllers;

import com.goan.football.models.Payment;
import com.goan.football.models.Registration;
import com.goan.football.models.Search;
import com.goan.football.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @MutationMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public Registration addRegistration(@Argument Registration registration){
        return registrationService.save(registration);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Registration> allRegistrations(@Argument Search search){
        return registrationService.all(search);
    }
}
