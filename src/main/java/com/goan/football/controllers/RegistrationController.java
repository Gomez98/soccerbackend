package com.goan.football.controllers;

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
@PreAuthorize("hasRole('ADMIN')")
public class RegistrationController {

    private final RegistrationService registrationService;

    @MutationMapping
    public Registration addRegistration(@Argument Registration registration){
        return registrationService.save(registration);
    }

    @QueryMapping
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public List<Registration> allRegistrations(@Argument Search search){
        return registrationService.all(search);
    }
}
