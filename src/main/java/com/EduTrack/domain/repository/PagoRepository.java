package com.EduTrack.domain.repository;

import com.EduTrack.persistance.entity.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoRepository {

    List<Pago> findByUsuarioId(String usuarioId);

    List<Pago> findByCursoId(Long cursoId);

    boolean existePago(String usuarioId, Long cursoId);

    Pago save(Pago pago);

    Optional<Pago> findById(Long id);
}
