package com.EduTrack.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    LocalDate fecha;


    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    Curso curso;

    @OneToMany(mappedBy = "asistencia", cascade = CascadeType.ALL, orphanRemoval = true)
    List<AsistenciaDetalle> detalles;

    public Asistencia() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public LocalDate getFecha() {return fecha;}

    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public Curso getCurso() {return curso;}

    public void setCurso(Curso curso) {this.curso = curso;}

    public List<AsistenciaDetalle> getDetalles() {return detalles;}

    public void setDetalles(List<AsistenciaDetalle> detalles) {this.detalles = detalles;}
}
