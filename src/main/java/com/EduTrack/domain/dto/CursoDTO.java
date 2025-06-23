package com.EduTrack.domain.dto;

import java.util.List;

public class CursoDTO {

    private Long id;


    private String nombre;


    private String descripcion;


    private String docenteId;


    private String docenteNombre;

    private Double precio;

    private List<String> estudiantesIds;

    private Boolean activo;

    private String requisitos;

    private String objetivos;

    private String incluye;

    private String imagen;

    private Boolean disponibleParaCompra;


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

    public Double getPrecio() {return precio;}

    public void setPrecio(Double precio) {this.precio = precio;}

    public List<String> getEstudiantesIds() {return estudiantesIds;}

    public void setEstudiantesIds(List<String> estudiantesIds) {this.estudiantesIds = estudiantesIds;}

    public Boolean getActivo() {return activo;}

    public void setActivo(Boolean activo) {this.activo = activo;}

    public String getRequisitos() {return requisitos;}

    public void setRequisitos(String requisitos) {this.requisitos = requisitos;}

    public String getObjetivos() {return objetivos;}

    public void setObjetivos(String objetivos) {this.objetivos = objetivos;}

    public String getIncluye() {return incluye;}

    public void setIncluye(String incluye) {this.incluye = incluye;}

    public String getImagen() {return imagen;}

    public void setImagen(String imagen) {this.imagen = imagen;}

    public Boolean getDisponibleParaCompra() {return disponibleParaCompra;}

    public void setDisponibleParaCompra(Boolean disponibleParaCompra) {this.disponibleParaCompra = disponibleParaCompra;}
}
