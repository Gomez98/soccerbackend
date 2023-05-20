package com.goan.football.controllers;

import com.goan.football.models.Student;
import com.goan.football.models.StudentRole;
import com.goan.football.services.StudentRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class StudentRoleController {

    private final StudentRoleService studentRoleService;

    @QueryMapping
    //@PreAuthorize("hasAuthority('admin:read')")
    public List<StudentRole> allStudentRoles(){
        return studentRoleService.all();
    }
}
