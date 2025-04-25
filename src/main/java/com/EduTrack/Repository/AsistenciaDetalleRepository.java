package com.EduTrack.Repository;

import com.EduTrack.Model.AsistenciaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaDetalleRepository extends JpaRepository<AsistenciaDetalle, Long> {
    // agregar métodos personalizados si es necesario
}
