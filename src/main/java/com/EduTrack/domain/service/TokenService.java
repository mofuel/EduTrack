package com.EduTrack.domain.service;

import com.EduTrack.domain.repository.TokenRepository;
import com.EduTrack.persistance.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token crearToken(Token token) {
        return tokenRepository.save(token);
    }

    public Optional<Token> obtenerPorToken(String token) {
        return tokenRepository.getByToken(token);
    }

    public boolean validarToken(String tokenStr) {
        Optional<Token> tokenOpt = tokenRepository.getByToken(tokenStr);
        if (tokenOpt.isEmpty()) return false;

        Token token = tokenOpt.get();

        return !token.isUsado() && token.getExpiracion().isAfter(LocalDateTime.now());
    }

    public void marcarComoUsado(Token token) {
        token.setUsado(true);
        tokenRepository.save(token);
    }

}
