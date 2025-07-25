package com.EduTrack.domain.dto;

public class ResenaDTO {

    private String comentario;

    private int estrellas;

    // Getters
    public String getComentario() {
        return comentario;
    }

    public int getEstrellas() {
        return estrellas;
    }

    // Setters
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }
}
