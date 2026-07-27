package com.EduTrack.persistence.crud;

import com.EduTrack.persistence.entity.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;

public interface CursoCrudRepository extends JpaRepository<Curso, Long> {

    @EntityGraph(attributePaths = {"docente"})
    List<Curso> findByDocente_Id(Long docenteId);

    @EntityGraph(attributePaths = {"docente"})
    List<Curso> findByNombreContainingIgnoreCase(String nombre);

    @EntityGraph(attributePaths = {"docente"})
    List<Curso> findByDocente_IdAndActivoTrue(Long docenteId);

    @EntityGraph(attributePaths = {"docente"})
    List<Curso> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    @EntityGraph(attributePaths = {"docente"})
    List<Curso> findByActivoTrue();

    @EntityGraph(attributePaths = {"docente"})
    List<Curso> findByActivoTrueAndDisponibleParaCompraTrue();

    @EntityGraph(attributePaths = {"docente"})
    List<Curso> findByNombreContainingIgnoreCaseAndActivoTrueAndDisponibleParaCompraTrue(String nombre);

    @EntityGraph(attributePaths = {"docente"})
    Page<Curso> findByActivoTrue(Pageable pageable);

    @EntityGraph(attributePaths = {"docente"})
    Page<Curso> findByActivoTrueAndDisponibleParaCompraTrue(Pageable pageable);

    @EntityGraph(attributePaths = {"docente"})
    Page<Curso> findByNombreContainingIgnoreCaseAndActivoTrueAndDisponibleParaCompraTrue(
            String nombre, Pageable pageable);
}