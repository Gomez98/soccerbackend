package com.goan.football.repositories;

import com.goan.football.models.StudentRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRoleRepository extends MongoRepository<StudentRole, String> {

}
