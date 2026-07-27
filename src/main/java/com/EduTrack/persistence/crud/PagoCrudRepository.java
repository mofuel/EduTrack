package com.EduTrack.persistence.crud;

import com.EduTrack.persistence.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoCrudRepository extends JpaRepository<Pago, Long> {

    // Listar todos los pagos de un usuario
    List<Pago> findByUsuario_Id(Long usuarioId);

    // Listar pagos por curso
    List<Pago> findByCurso_Id(Long cursoId);

    // Buscar un pago específico por usuario y curso
    boolean existsByUsuario_IdAndCurso_Id(Long usuarioId, Long cursoId);
}
