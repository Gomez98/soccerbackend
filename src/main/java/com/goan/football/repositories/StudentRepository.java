package com.goan.football.repositories;

import com.goan.football.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{ 'firstName' : { $regex: ?0, $options: 'i' } }")
    Page<Student> findAllByName(String name, Pageable pageable);
}
