package com.EduTrack.domain.dto;

public class ProgresoCursoDTO {

    private Long cursoId;
    private String nombreCurso;
    private double porcentajeAvance;

    public ProgresoCursoDTO() {
    }

    public Long getCursoId() {return cursoId;}

    public void setCursoId(Long cursoId) {this.cursoId = cursoId;}

    public String getNombreCurso() {return nombreCurso;}

    public void setNombreCurso(String nombreCurso) {this.nombreCurso = nombreCurso;}

    public double getPorcentajeAvance() {return porcentajeAvance;}

    public void setPorcentajeAvance(double porcentajeAvance) {this.porcentajeAvance = porcentajeAvance;}
}
