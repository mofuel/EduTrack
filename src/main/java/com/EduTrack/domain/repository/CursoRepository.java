package com.EduTrack.domain.repository;

import com.EduTrack.persistance.entity.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoRepository {
    List<Curso> getAll();
    Optional<Curso> getById(Long id);
    Curso save(Curso curso);
    void delete(Long id);
}
