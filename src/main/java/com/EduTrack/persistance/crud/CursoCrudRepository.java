package com.EduTrack.persistance.crud;

import com.EduTrack.persistance.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoCrudRepository extends JpaRepository<Curso, Long> {
    // Obtener cursos por docente
    List<Curso> findByDocente_Id(String docenteId);

    // Buscar cursos donde el estudiante esté inscrito
    List<Curso> findByEstudiantes_Id(String estudianteId);

    // Buscar por nombre de curso que contenga una palabra (opcional para búsquedas)
    List<Curso> findByNombreContainingIgnoreCase(String nombre);


    // ✅ Métodos con soft delete (activo = true)
    List<Curso> findByDocente_IdAndActivoTrue(String docenteId);
    List<Curso> findByEstudiantes_IdAndActivoTrue(String estudianteId);
    List<Curso> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);
    List<Curso> findByActivoTrue();
}
