package com.EduTrack.Repository;

import com.EduTrack.Model.Notas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface NotasRepository extends JpaRepository<Notas, Long> {
    List<Notas> findByEstudianteId(String estudianteId); //Lista de notas de un Estudiante por id

    List<Notas> findByEstudianteIdAndFechaBetween(String estudianteId, Date fechaInicio, Date fechaFin);

    // List<Notas> findByEstudianteIdAndCurso(String estudianteId, String curso);
}
