package com.EduTrack.domain.repository;

import com.EduTrack.persistance.entity.Token;

import java.util.Optional;

public interface TokenRepository {
    Optional<Token> getByToken(String token);
    Token save(Token token);
    void delete(Integer id);
}
