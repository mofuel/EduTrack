package com.EduTrack.Model;

import jakarta.persistence.OneToMany;

import java.util.List;

public class Curso {
    Long id;
    String nom_curso;
    String descrip_curso;
    Usuarios docente;
    List<Usuarios> estudiantes;

    //@OneToMany(mappedBy = "curso")
    //private List<Notas>notas;
}
