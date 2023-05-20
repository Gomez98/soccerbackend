package com.goan.football.repositories;

import com.goan.football.models.Membership;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MembershipRepository extends MongoRepository<Membership, String> {

    Optional<Membership> findByActive(boolean active);

}
