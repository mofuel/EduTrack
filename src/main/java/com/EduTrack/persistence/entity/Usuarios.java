package com.EduTrack.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String nombre;

    @Column
    String apellido;

    @Column(unique = true, nullable = false, length = 8)
    String dni;

    @Column(unique = true, nullable = false)
    String email;

    @Column
    String telefono;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol = RolUsuario.ROLE_estudiante;

    @Column
    String password;


    private LocalDateTime fechaRegistro;

    private LocalDateTime ultimoAcceso;

    private boolean activo = true;

    private boolean cuentaBloqueada = false;

    public enum RolUsuario {
        ROLE_admin, ROLE_docente, ROLE_estudiante
    }



    //Constructor vacio
    public Usuarios() {}

    //Getter y Setter

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public RolUsuario getRol() {return rol;}

    public void setRol(RolUsuario rol) {this.rol = rol;}

    public LocalDateTime getFechaRegistro() {return fechaRegistro;}

    public void setFechaRegistro(LocalDateTime fechaRegistro) {this.fechaRegistro = fechaRegistro;}

    public LocalDateTime getUltimoAcceso() {return ultimoAcceso;}

    public void setUltimoAcceso(LocalDateTime ultimoAcceso) {this.ultimoAcceso = ultimoAcceso;}

    public boolean isActivo() {return activo;}

    public void setActivo(boolean activo) {this.activo = activo;}

    public boolean isCuentaBloqueada() {return cuentaBloqueada;}

    public void setCuentaBloqueada(boolean cuentaBloqueada) {this.cuentaBloqueada = cuentaBloqueada;}
}
