package com.EduTrack.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Usuarios docente;

//    @ManyToMany
//    @JoinTable(
//            name = "curso_estudiantes",
//            joinColumns = @JoinColumn(name = "curso_id"),
//            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
//    )
//    private List<Usuarios> estudiantes;

    // Constructor vacío
    public Curso() {}

    // Getters y setters
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Usuarios getDocente() {return docente;}

    public void setDocente(Usuarios docente) {this.docente = docente;}

//    public List<Usuarios> getEstudiantes() {return estudiantes;}
//
//    public void setEstudiantes(List<Usuarios> estudiantes) {this.estudiantes = estudiantes;}
}
