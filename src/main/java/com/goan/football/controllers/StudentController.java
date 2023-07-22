package com.goan.football.controllers;

import com.goan.football.models.Search;
import com.goan.football.models.Student;
import com.goan.football.services.StudentService;
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
public class StudentController {

    private final StudentService studentService;

    @MutationMapping
    public Student addStudent(@Argument Student student){
        return studentService.save(student);
    }

    @MutationMapping
    public Student updateStudent(@Argument Student student){
        return studentService.update(student);
    }

    @QueryMapping
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public Student getStudent(@Argument String id){
        return studentService.get(id);
    }

    @QueryMapping
    @PreAuthorize("hasAnyAuthority('user:read', 'admin:read')")
    public List<Student> allStudents(@Argument Search search){
        return studentService.all(search);
    }

}
