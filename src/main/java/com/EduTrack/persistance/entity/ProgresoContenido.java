package com.EduTrack.persistance.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "progreso_contenido", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "contenido_id"})
})
public class ProgresoContenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "contenido_id", nullable = false)
    private Contenido contenido;

    private LocalDateTime fechaVisualizacion = LocalDateTime.now();

    //Getters y Setters

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Usuarios getUsuario() {return usuario;}

    public void setUsuario(Usuarios usuario) {this.usuario = usuario;}

    public Contenido getContenido() {return contenido;}

    public void setContenido(Contenido contenido) {this.contenido = contenido;}

    public LocalDateTime getFechaVisualizacion() {return fechaVisualizacion;}

    public void setFechaVisualizacion(LocalDateTime fechaVisualizacion) {this.fechaVisualizacion = fechaVisualizacion;}
}
