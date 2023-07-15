package com.goan.football.controllers;

import com.goan.football.models.Representative;
import com.goan.football.models.Search;
import com.goan.football.services.RepresentativeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class RepresentativeController {

    private final RepresentativeService representativeService;

    @MutationMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public Representative addRepresentative(@Argument Representative representative){
        return representativeService.save(representative);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public Representative updateRepresentative(@Argument Representative representative){
        return representativeService.update(representative);
    }


    @QueryMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public Representative getRepresentative(@Argument String id){
        return representativeService.get(id);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Representative> allRepresentatives(@Argument Search search){
        return representativeService.all(search);
    }

}
