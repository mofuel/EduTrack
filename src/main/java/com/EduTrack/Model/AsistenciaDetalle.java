package com.EduTrack.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "asistencia_detalle")
public class AsistenciaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "asistencia_id", nullable = false)
    @JsonBackReference
    Asistencia asistencia;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    Usuarios estudiante;

    @Column
    Boolean presente;

    public AsistenciaDetalle() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Asistencia getAsistencia() {return asistencia;}

    public void setAsistencia(Asistencia asistencia) {this.asistencia = asistencia;}

    public Usuarios getEstudiante() {return estudiante;}

    public void setEstudiante(Usuarios estudiante) {this.estudiante = estudiante;}

    public Boolean getPresente() {return presente;}

    public void setPresente(Boolean presente) {this.presente = presente;}
}
