package com.EduTrack.Model;

import java.time.LocalDate;

public class Tarea {
    Long id;

    String nom_tarea;
    String descrip_tarea;

    LocalDate fecha_entrega;
    Curso curso;
}
