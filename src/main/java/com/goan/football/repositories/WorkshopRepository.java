package com.goan.football.repositories;

import com.goan.football.models.Workshop;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WorkshopRepository extends MongoRepository<Workshop, String> {

    Optional<Workshop> findByName(String name);
}
