package com.EduTrack.domain.dto;

import java.time.LocalDateTime;

public class TokenDTO {
    private String token;
    private LocalDateTime expiracion;

    public TokenDTO() {}

    public TokenDTO(String token, LocalDateTime expiracion) {
        this.token = token;
        this.expiracion = expiracion;
    }


    // getters y setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public LocalDateTime getExpiracion() { return expiracion; }
    public void setExpiracion(LocalDateTime expiracion) { this.expiracion = expiracion; }
}
