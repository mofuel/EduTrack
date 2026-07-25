package com.EduTrack.domain.repository;

import com.EduTrack.persistence.entity.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoRepository {

    List<Pago> findByUsuarioId(Long usuarioId);

    List<Pago> findByCursoId(Long cursoId);

    boolean existePago(Long usuarioId, Long cursoId);

    Pago save(Pago pago);

    Optional<Pago> findById(Long id);
}
