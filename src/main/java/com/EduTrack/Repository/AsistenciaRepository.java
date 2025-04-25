package com.EduTrack.Repository;

import com.EduTrack.Model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    // agregar métodos personalizados si es necesario
}

