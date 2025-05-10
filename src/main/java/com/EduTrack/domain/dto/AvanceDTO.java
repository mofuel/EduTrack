package com.EduTrack.domain.dto;

public class AvanceDTO {

    private Long id;


    private String descripcion;


    private Double progreso;


    private Long estudianteId;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Double getProgreso() {return progreso;}

    public void setProgreso(Double progreso) {this.progreso = progreso;}

    public Long getEstudianteId() {return estudianteId;}

    public void setEstudianteId(Long estudianteId) {this.estudianteId = estudianteId;}
}
