package com.EduTrack.persistence.crud;

import com.EduTrack.persistence.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoCrudRepository extends JpaRepository<Curso, Long> {
    // Obtener cursos por docente
    List<Curso> findByDocente_Id(Long docenteId);


    // Buscar por nombre de curso que contenga una palabra (opcional para búsquedas)
    List<Curso> findByNombreContainingIgnoreCase(String nombre);


    // Métodos con soft delete (activo = true)
    List<Curso> findByDocente_IdAndActivoTrue(Long docenteId);
    List<Curso> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);
    List<Curso> findByActivoTrue();

    // Cursos activos y disponibles para compra (catálogo público)
    List<Curso> findByActivoTrueAndDisponibleParaCompraTrue();

    // Buscar por nombre, solo cursos activos y disponibles para compra
    List<Curso> findByNombreContainingIgnoreCaseAndActivoTrueAndDisponibleParaCompraTrue(String nombre);


}
