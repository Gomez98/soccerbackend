package com.goan.football.controllers;

import com.goan.football.models.Representative;
import com.goan.football.services.RepresentativeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class RepresentativeController {


    private final RepresentativeService representativeService;

    @QueryMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public Representative addRepresentative(@Argument Representative representative){
        return representativeService.save(representative);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public Representative getRepresentative(@Argument String id){
        return representativeService.get(id);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Representative> allRepresentatives(@Argument String term, @Argument int page, @Argument int size){
        return representativeService.all(term, page, size);
    }

}
