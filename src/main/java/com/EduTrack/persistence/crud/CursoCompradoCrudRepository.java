package com.EduTrack.persistence.crud;

import com.EduTrack.persistence.entity.CursoComprado;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoCompradoCrudRepository extends JpaRepository<CursoComprado, Long> {

    @EntityGraph(attributePaths = {"curso", "curso.docente"})
    List<CursoComprado> findByUsuario_Id(Long usuarioId);

    boolean existsByUsuario_IdAndCurso_Id(Long usuarioId, Long cursoId);

}
