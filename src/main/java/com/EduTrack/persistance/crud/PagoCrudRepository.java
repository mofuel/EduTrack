package com.EduTrack.persistance.crud;

import com.EduTrack.persistance.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoCrudRepository extends JpaRepository<Pago, Long> {

    // Listar todos los pagos de un usuario
    List<Pago> findByUsuario_Id(String usuarioId);

    // Listar pagos por curso
    List<Pago> findByCurso_Id(Long cursoId);

    // Buscar un pago específico por usuario y curso
    boolean existsByUsuario_IdAndCurso_Id(String usuarioId, Long cursoId);
}
