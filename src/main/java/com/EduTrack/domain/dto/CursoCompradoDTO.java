package com.EduTrack.domain.dto;

import java.time.LocalDateTime;

public class CursoCompradoDTO {
    private Long id;
    private String usuarioId;
    private Long cursoId;
    private String nombreCurso;
    private Double precio;
    private String imagen;
    private LocalDateTime fechaCompra;
    private String docenteNombre;

    //Getters y Setters


    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getUsuarioId() {return usuarioId;}

    public void setUsuarioId(String usuarioId) {this.usuarioId = usuarioId;}

    public Long getCursoId() {return cursoId;}

    public void setCursoId(Long cursoId) {this.cursoId = cursoId;}

    public String getNombreCurso() {return nombreCurso;}

    public void setNombreCurso(String nombreCurso) {this.nombreCurso = nombreCurso;}

    public Double getPrecio() {return precio;}

    public void setPrecio(Double precio) {this.precio = precio;}

    public String getImagen() {return imagen;}

    public void setImagen(String imagen) {this.imagen = imagen;}

    public LocalDateTime getFechaCompra() {return fechaCompra;}

    public void setFechaCompra(LocalDateTime fechaCompra) {this.fechaCompra = fechaCompra;}

    public String getDocenteNombre() {return docenteNombre;}

    public void setDocenteNombre(String docenteNombre) {this.docenteNombre = docenteNombre;}

}
