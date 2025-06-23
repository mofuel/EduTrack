package com.EduTrack.domain.repository;

import com.EduTrack.persistance.entity.Carrito;

import java.util.List;
import java.util.Optional;

public interface CarritoRepository {

    List<Carrito> getByUsuarioId(String usuarioId);

    Optional<Carrito> getByUsuarioIdAndCursoId(String usuarioId, Long cursoId);

    Carrito save(Carrito carrito);

    void deleteByUsuarioIdAndCursoId(String usuarioId, Long cursoId);
}
