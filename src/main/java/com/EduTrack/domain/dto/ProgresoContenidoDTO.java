package com.EduTrack.domain.dto;

import java.time.LocalDateTime;

public class ProgresoContenidoDTO {

    private Long id;
    private Long contenidoId;
    private String tituloContenido;
    private Long moduloId;
    private String nombreCurso;
    private String usuarioId;
    private LocalDateTime fechaVisualizacion;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTituloContenido() {return tituloContenido;}

    public void setTituloContenido(String tituloContenido) {this.tituloContenido = tituloContenido;}

    public Long getContenidoId() {return contenidoId;}

    public void setContenidoId(Long contenidoId) {this.contenidoId = contenidoId;}

    public Long getModuloId() {return moduloId;}

    public void setModuloId(Long moduloId) {this.moduloId = moduloId;}

    public String getUsuarioId() {return usuarioId;}

    public void setUsuarioId(String usuarioId) {this.usuarioId = usuarioId;}

    public String getNombreCurso() {return nombreCurso;}

    public void setNombreCurso(String nombreCurso) {this.nombreCurso = nombreCurso;}

    public LocalDateTime getFechaVisualizacion() {return fechaVisualizacion;}

    public void setFechaVisualizacion(LocalDateTime fechaVisualizacion) {this.fechaVisualizacion = fechaVisualizacion;}
}
