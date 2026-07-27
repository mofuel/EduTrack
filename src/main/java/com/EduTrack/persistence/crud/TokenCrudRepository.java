package com.EduTrack.persistence.crud;

import com.EduTrack.persistence.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenCrudRepository  extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
}
