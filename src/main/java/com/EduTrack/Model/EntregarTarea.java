package com.EduTrack.Model;

import java.time.LocalDate;

public class EntregarTarea {
    Long id;
    Usuarios estudiante;
    Tarea tarea;
    String archivo_url;
    LocalDate fecha_entrega;
    Integer nota;
}