package com.EduTrack.domain.repository;

import com.EduTrack.persistance.entity.CursoComprado;

import java.util.List;
import java.util.Optional;


public interface CursoCompradoRepository {

    List<CursoComprado> findByUsuarioId(String usuarioId);

    boolean existeCompra(String usuarioId, Long cursoId);

    CursoComprado save(CursoComprado cursoComprado);

    Optional<CursoComprado> findById(Long id);
}
