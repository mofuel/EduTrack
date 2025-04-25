package com.EduTrack.Repository;

import com.EduTrack.Model.Asistencia;
import com.EduTrack.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    // agregar métodos personalizados si es necesario
    Optional<Asistencia> findByFechaAndCurso(LocalDate fecha, Curso curso);
}

