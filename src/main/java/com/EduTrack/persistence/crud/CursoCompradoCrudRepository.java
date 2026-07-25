package com.EduTrack.persistence.crud;

import com.EduTrack.persistence.entity.CursoComprado;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoCompradoCrudRepository extends JpaRepository<CursoComprado, Long> {

    @EntityGraph(attributePaths = {"curso", "curso.docente"})
    List<CursoComprado> findByUsuario_Id(String usuarioId);

    boolean existsByUsuario_IdAndCurso_Id(String usuarioId, Long cursoId);

}
