package com.EduTrack.domain.dto;

public class CursoDTO {

    private Long id;


    private String nombre;


    private String descripcion;


    private String docenteId;


    private String docenteNombre;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDocenteId() {return docenteId;}

    public void setDocenteId(String docenteId) {this.docenteId = docenteId;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getDocenteNombre() {return docenteNombre;}

    public void setDocenteNombre(String docenteNombre) {this.docenteNombre = docenteNombre;}
}
