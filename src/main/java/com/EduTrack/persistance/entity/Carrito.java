package com.EduTrack.persistance.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "carrito", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "curso_id"})
})
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "fecha_agregado")
    private LocalDateTime fechaAgregado = LocalDateTime.now();

    //Getters y Setters
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Usuarios getUsuario() {return usuario;}

    public void setUsuario(Usuarios usuario) {this.usuario = usuario;}

    public Curso getCurso() {return curso;}

    public void setCurso(Curso curso) {this.curso = curso;}

    public LocalDateTime getFechaAgregado() {return fechaAgregado;}

    public void setFechaAgregado(LocalDateTime fechaAgregado) {this.fechaAgregado = fechaAgregado;}
}
