package com.goan.football.repositories;

import com.goan.football.models.Representative;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepresentativeRepository extends MongoRepository<Representative, String> {

    @Query("{ 'name' : { $regex: ?0, $options: 'i' } }")
    Page<Representative> findAllByName(String name, Pageable pageable);
}
