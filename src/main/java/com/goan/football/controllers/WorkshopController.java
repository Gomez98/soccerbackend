package com.goan.football.controllers;

import com.goan.football.models.Search;
import com.goan.football.models.Student;
import com.goan.football.models.Workshop;
import com.goan.football.services.WorkshopService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class WorkshopController {

    private final WorkshopService workshopService;

    @MutationMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public Workshop addWorkshop(@Argument Workshop workshop){
        return workshopService.save(workshop);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public Workshop updateWorkshop(@Argument Workshop workshop){
        return workshopService.update(workshop);
    }

    @QueryMapping
    //@PreAuthorize("hasAuthority('admin:read')")
    public Workshop getWorkshop(@Argument String id){
        return workshopService.get(id);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Workshop> allWorkshops(@Argument Search search){
        return workshopService.all(search);
    }


}
