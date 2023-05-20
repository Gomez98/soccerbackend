package com.goan.football.services;

import com.goan.football.models.Student;

import java.util.List;

public interface StudentService {

    Student save(Student student);

    Student get(String id);

    List<Student> all(String term, int page, int size);
}
