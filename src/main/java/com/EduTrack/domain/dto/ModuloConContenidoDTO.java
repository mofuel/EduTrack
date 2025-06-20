package com.EduTrack.domain.dto;

import java.util.List;

public class ModuloConContenidoDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private Long cursoId;
    private List<ContenidoDTO> contenidos;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Long getCursoId() {return cursoId;}

    public void setCursoId(Long cursoId) {this.cursoId = cursoId;}

    public List<ContenidoDTO> getContenidos() { return contenidos; }
    public void setContenidos(List<ContenidoDTO> contenidos) { this.contenidos = contenidos; }
}
