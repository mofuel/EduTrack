package com.EduTrack.domain.repository;

import com.EduTrack.persistence.entity.Carrito;

import java.util.List;
import java.util.Optional;

public interface CarritoRepository {

    List<Carrito> getByUsuarioId(Long usuarioId);

    Optional<Carrito> getByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);

    Carrito save(Carrito carrito);

    void deleteByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
}
