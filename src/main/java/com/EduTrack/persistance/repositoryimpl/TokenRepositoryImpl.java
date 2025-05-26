package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.domain.repository.TokenRepository;
import com.EduTrack.persistance.crud.TokenCrudRepository;
import com.EduTrack.persistance.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    private TokenCrudRepository crud;

    @Override
    public Optional<Token> getByToken(String token) {
        return crud.findByToken(token);
    }

    @Override
    public Token save(Token token) {
        return crud.save(token);
    }

    @Override
    public void delete(Integer id) {
        crud.deleteById(id);
    }
}
