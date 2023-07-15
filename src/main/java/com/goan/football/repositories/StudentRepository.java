package com.goan.football.repositories;

import com.goan.football.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{ 'firstName' : { $regex: ?0, $options: 'i' } }")
    Page<Student> findAllByName(String name, Pageable pageable);

    @Query("{ 'firstName' : { $regex: ?0, $options: 'i' } }")
    List<Student> findAllByName(String name);

    @Query("{ 'active' : true }")
    List<Student> findAllByActive();
}
