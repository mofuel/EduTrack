package com.EduTrack.Model;

import java.time.LocalDate;
import java.util.List;

public class Asistencia {
    Long id;
    LocalDate fecha;
    Curso curso;
    List<AsistenciaDetalle> detalles;
}
