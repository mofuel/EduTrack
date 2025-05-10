package com.EduTrack.persistance.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "avances")
public class Avance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descripcion;

    @Column
    private Double progreso;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios estudiante;

    public Avance() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Usuarios getEstudiante() {return estudiante;}

    public void setEstudiante(Usuarios estudiante) {this.estudiante = estudiante;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Double getProgreso() {return progreso;}

    public void setProgreso(Double progreso) {this.progreso = progreso;}
}
