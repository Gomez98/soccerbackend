package com.goan.football.controllers;

import com.goan.football.models.Student;
import com.goan.football.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @MutationMapping
    //@PreAuthorize("hasAuthority('admin:write')")
    public Student addStudent(@Argument Student student){
        return studentService.save(student);
    }

    @QueryMapping
    //@PreAuthorize("hasAuthority('admin:read')")
    public Student getStudent(@Argument String id){
        return studentService.get(id);
    }

    @QueryMapping
    //@PreAuthorize("hasAuthority('admin:read')")
    public List<Student> allStudents(@Argument String term, @Argument int page, @Argument int size){
        return studentService.all(term, page, size);
    }

}
