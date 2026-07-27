package com.EduTrack.domain.repository;

import com.EduTrack.persistence.entity.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CursoRepository {
    List<Curso> getAll();
    Optional<Curso> getById(Long id);
    Curso save(Curso curso);

    // Reemplazar delete con soft delete
    void softDelete(Long id);

    // Buscar solo cursos activos por docente
    List<Curso> getByDocenteId(Long docenteId);

    // Buscar solo cursos activos por estudiante
    List<Curso> getByEstudianteId(Long estudianteId);

    // Búsqueda solo en cursos activos
    List<Curso> searchByNombre(String nombre);

    // Obtener cursos activos y disponibles para compra
    List<Curso> getDisponiblesParaCompra();

    // Búsqueda por nombre solo en cursos activos y disponibles
    List<Curso> searchDisponiblesPorNombre(String nombre);

    Page<Curso> getAll(Pageable pageable);

    Page<Curso> getDisponiblesParaCompra(Pageable pageable);

    Page<Curso> searchDisponiblesPorNombre(String nombre, Pageable pageable);
}
