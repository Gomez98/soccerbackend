package com.goan.football.controllers;

import com.goan.football.models.Due;
import com.goan.football.models.Search;
import com.goan.football.services.DueService;
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
@PreAuthorize("hasRole('ADMIN')")
public class DueController {

    private final DueService dueService;

    @MutationMapping
    public Due addDue(@Argument Due due){
        return dueService.save(due);
    }

    @QueryMapping
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public List<Due> allDues(@Argument Search search){
        return dueService.all(search);
    }

    @MutationMapping
    public List<Due> payDues(@Argument List<String> dueIds){
        return dueService.payDues(dueIds);
    }

    @MutationMapping
    public List<Due> allDuesByStudentId(@Argument String studentId){
        return dueService.all(studentId);
    }
}
