package com.goan.football.repositories;

import com.goan.football.models.token.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {
    Optional<Token> findByToken(String token);

    @Query("{ 'user.id' : ?0, 'valid' : true }")
    List<Token> findAllValidTokenByUser(String id);

    Token findByUserId(String userId);
}
