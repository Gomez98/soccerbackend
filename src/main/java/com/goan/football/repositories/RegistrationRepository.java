package com.goan.football.repositories;


import com.goan.football.models.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RegistrationRepository extends MongoRepository<Registration, String> {

    List<Registration> findAllByStudentId(String id);
}
