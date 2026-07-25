package com.EduTrack.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "progreso_curso", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "curso_id"})
})
public class ProgresoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    private Double porcentaje = 0.0;

    @Enumerated(EnumType.STRING)
    private EstadoProgreso estado = EstadoProgreso.NO_INICIADO;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_completado")
    private LocalDateTime fechaCompletado;

    public enum EstadoProgreso {
        NO_INICIADO, EN_CURSO, COMPLETADO
    }

    public ProgresoCurso() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Usuarios getUsuario() { return usuario; }

    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }

    public Curso getCurso() { return curso; }

    public void setCurso(Curso curso) { this.curso = curso; }

    public Double getPorcentaje() { return porcentaje; }

    public void setPorcentaje(Double porcentaje) { this.porcentaje = porcentaje; }

    public EstadoProgreso getEstado() { return estado; }

    public void setEstado(EstadoProgreso estado) { this.estado = estado; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }

    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaCompletado() { return fechaCompletado; }
    
    public void setFechaCompletado(LocalDateTime fechaCompletado) { this.fechaCompletado = fechaCompletado; }
}