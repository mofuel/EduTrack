package com.EduTrack.domain.repository;

import com.EduTrack.persistance.entity.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoRepository {
    List<Curso> getAll();
    Optional<Curso> getById(Long id);
    Curso save(Curso curso);

    // Reemplazar delete con soft delete
    void softDelete(Long id);

    // Buscar solo cursos activos por docente
    List<Curso> getByDocenteId(String docenteId);

    // Buscar solo cursos activos por estudiante
    List<Curso> getByEstudianteId(String estudianteId);

    // Búsqueda solo en cursos activos
    List<Curso> searchByNombre(String nombre);

    // Obtener cursos activos y disponibles para compra
    List<Curso> getDisponiblesParaCompra();

    // Búsqueda por nombre solo en cursos activos y disponibles
    List<Curso> searchDisponiblesPorNombre(String nombre);

}
