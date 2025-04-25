package com.EduTrack.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "usuarios")
public class Usuarios {

    @Id
    String id;

    @Column
    String Nombre;

    @Column
    String Apellido;

    @Column
    String dni;

    @Column
    String email;

    @Column
    String telefono;

    @Column
    String rol;

    @Column
    String contraseña;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Notas> notas;

    //Constructor vacio
    public Usuarios() {}

    //Metodos Getter y Setter
    public String getNombre() {return Nombre;}

    public void setNombre(String nombre) {Nombre = nombre;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getApellido() {return Apellido;}

    public void setApellido(String apellido) {Apellido = apellido;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getRol() {return rol;}

    public void setRol(String rol) {this.rol = rol;}

    public String getContraseña() {return contraseña;}

    public void setContraseña(String contraseña) {this.contraseña = contraseña;}

}
