package com.goan.football.services.impl;

import com.goan.football.models.*;
import com.goan.football.repositories.StudentRepository;
import com.goan.football.services.StudentService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.goan.football.utils.ModelUtils.prepare;


@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        prepare(student);
        student.setFullName(student.getFirstName() + " " + student.getLastName());
        student.setActive(true);
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student entity) {

        if (entity == null) {
            return null;
        }
        if (entity.getId() == null) {
            return null;
        }
        Student student = studentRepository.findById(entity.getId()).orElse(null);
        if(student != null){
            student.setDeleted(entity.getDeleted() != null ? entity.getDeleted() : student.getDeleted());
            student.setAge(entity.getAge() != null ? entity.getAge() : student.getAge());
            student.setDni(entity.getDni() != null ? entity.getDni() : student.getDni());
            student.setEmail(entity.getEmail() != null ? entity.getEmail() : student.getEmail());
            student.setFirstName(entity.getFirstName() != null ? entity.getFirstName() : student.getFirstName());
            student.setLastName(entity.getLastName() != null ? entity.getLastName() : student.getLastName());
            student.setFullName(entity.getFullName() != null ? entity.getFullName() : student.getFirstName() + entity.getLastName());
            student.setModifiedAt(String.valueOf(new Date().getTime()));
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public Student get(String id) {
        Student student = studentRepository.findById(id).orElse(null);
        log.info("Student: "+ student.toString());
        return student;
    }

    @Override
    public List<Student> all(Search search) {

        List<Student> students;
        String term = null;
        Integer page = null;
        Integer size = null;

        if (search != null && search.getTerm() != null) {
            term = search.getTerm();
        }
        if (search != null && search.getPage() != null) {
            page = search.getPage();
        }
        if (search != null && search.getSize() != null) {
            size = search.getSize();
        }

        if (page == null && size == null && term == null){
            students = studentRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(Student::getModifiedAt).reversed())
                    .collect(Collectors.toList());
        } else if (page != null && size !=null){
            if (term == null){
                term = "";
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
            Page<Student> studentPage = studentRepository.findAllByName(term, pageable);
            students = studentPage.get().toList();
        } else {
            students = studentRepository.findAllByName(term);
        }

        students = students.stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());
        return students;
    }

    @Override
    public List<Student> all(String id) {
        return null;
    }

}
