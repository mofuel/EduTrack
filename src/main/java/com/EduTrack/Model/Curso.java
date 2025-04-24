package com.EduTrack.Model;

import java.util.List;

public class Curso {
    Long id;
    String nom_curso;
    String descrip_curso;
    Usuarios docente;
    List<Usuarios> estudiantes;
}
