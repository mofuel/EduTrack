package com.EduTrack.Model;

import java.util.Date;

public class Notas {
    Long id;
    String usuarioId;
    Curso curso;
    Usuarios estudiante;
    Integer calificacion; //calificacion numerica
    Date fecha;
    String comentario;
}
