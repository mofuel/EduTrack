package com.EduTrack.domain.repository;

import com.EduTrack.persistence.entity.CursoComprado;

import java.util.List;
import java.util.Optional;


public interface CursoCompradoRepository {

    List<CursoComprado> findByUsuarioId(Long usuarioId);

    boolean existeCompra(Long usuarioId, Long cursoId);

    CursoComprado save(CursoComprado cursoComprado);

    Optional<CursoComprado> findById(Long id);
}
