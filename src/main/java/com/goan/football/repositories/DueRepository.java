package com.goan.football.repositories;

import com.goan.football.models.Due;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DueRepository extends MongoRepository<Due, String> {

    Optional<Due> findByStudentId(String id);

    List<Due> findAllByStudentId(String id);

    List<Due> findAllByStudentIdAndDueDate(String id, int monthValue);

    List<Due> findAllByStudentIdAndRegistrationId(String studentId, String registrationId);

    List<Due> findAllByStudentIdAndRegistrationIdAndCreationDate(String studentTd, String registrationId, LocalDate creationDate);

    List<Due> findAllByStudentIdAndStatus(String id, String name);

    @Query("{ 'name' : { $regex: ?0, $options: 'i' } }")
    Page<Due> findAllByName(String term, Pageable pageable);

    @Query("{ 'name' : { $regex: ?0, $options: 'i' } }")
    List<Due> findAllByName(String term);
}
