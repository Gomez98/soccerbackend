package com.goan.football.services.impl;

import com.goan.football.models.StudentRole;
import com.goan.football.repositories.StudentRoleRepository;
import com.goan.football.services.StudentRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StudentRoleServiceImpl implements StudentRoleService {

    private final StudentRoleRepository studentRoleRepository;

    @Override
    public List<StudentRole> all() {
        List<StudentRole> studentRoles = studentRoleRepository.findAll();

        return studentRoles;
    }
}
