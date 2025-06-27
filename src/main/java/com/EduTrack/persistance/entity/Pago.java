package com.EduTrack.persistance.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    private String metodoPago;       // "yape", "plin", "tarjeta", etc.
    private String referenciaPago;   // puede ser el número de operación o parte del número de tarjeta
    private LocalDateTime fechaPago = LocalDateTime.now();

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Usuarios getUsuario() {return usuario;}

    public void setUsuario(Usuarios usuario) {this.usuario = usuario;}

    public Curso getCurso() {return curso;}

    public void setCurso(Curso curso) {this.curso = curso;}

    public String getMetodoPago() {return metodoPago;}

    public void setMetodoPago(String metodoPago) {this.metodoPago = metodoPago;}

    public String getReferenciaPago() {return referenciaPago;}

    public void setReferenciaPago(String referenciaPago) {this.referenciaPago = referenciaPago;}

    public LocalDateTime getFechaPago() {return fechaPago;}

    public void setFechaPago(LocalDateTime fechaPago) {this.fechaPago = fechaPago;}
}
