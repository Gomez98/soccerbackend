package com.goan.football.services.impl;

import com.goan.football.models.Membership;
import com.goan.football.models.Student;
import com.goan.football.models.StudentRole;
import com.goan.football.models.StudentRoleName;
import com.goan.football.repositories.MembershipRepository;
import com.goan.football.repositories.StudentRepository;
import com.goan.football.services.StudentService;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final MembershipRepository membershipRepository;


    @Override
    public Student save(Student student) {
        student.setCreatedAt(String.valueOf(new Date().getTime()));
        student.setModifiedAt(String.valueOf(new Date().getTime()));
        student.setDeleted(false);
        Membership membership = membershipRepository.findByActive(true).orElse(null);
        if (membership == null){
            return null;
        }
        if (student.getStudentRole() == StudentRoleName.BASIC_STUDENT.name()){
            student.setPayment(membership.getPrice() * 0.9);
        }
        if (student.getStudentRole() == StudentRoleName.PRO_STUDENT.name()){
            student.setPayment(membership.getPrice() * 0.75);
        }
        return studentRepository.save(student);
    }

    @Override
    public Student get(String id) {
        Student student = studentRepository.findById(id).orElse(null);
        log.info("Student: "+ student.toString());
        return student;
    }

    @Override
    public List<Student> all(String term, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Student> studentPage =studentRepository.findAllByName(term, pageable);
        return studentPage.get().toList();
    }
}
