package com.EduTrack.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom_curso;

    private String descrip_curso;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Usuarios docente;

    @ManyToMany
    @JoinTable(
            name = "curso_estudiantes",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private List<Usuarios> estudiantes;

    // Constructor vacío
    public Curso() {}

    // Getters y setters
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNom_curso() {return nom_curso;}

    public void setNom_curso(String nom_curso) {this.nom_curso = nom_curso;}

    public String getDescrip_curso() {return descrip_curso;}

    public void setDescrip_curso(String descrip_curso) {this.descrip_curso = descrip_curso;}

    public Usuarios getDocente() {return docente;}

    public void setDocente(Usuarios docente) {this.docente = docente;}

    public List<Usuarios> getEstudiantes() {return estudiantes;}

    public void setEstudiantes(List<Usuarios> estudiantes) {this.estudiantes = estudiantes;}
}
