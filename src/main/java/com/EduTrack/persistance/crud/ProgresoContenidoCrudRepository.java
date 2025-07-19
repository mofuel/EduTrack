package com.EduTrack.persistance.crud;

import com.EduTrack.domain.projection.ProgresoCursoProjection;
import com.EduTrack.persistance.entity.ProgresoContenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProgresoContenidoCrudRepository extends JpaRepository<ProgresoContenido, Long> {

    List<ProgresoContenido> findByUsuarioId(String usuarioId);

    boolean existsByUsuarioIdAndContenidoId(String usuarioId, Long contenidoId);

    Optional<ProgresoContenido> findByUsuarioIdAndContenidoId(String usuarioId, Long contenidoId);

    @Query("""
        SELECT c.modulo.curso.id AS cursoId,
               c.modulo.curso.nombre AS nombreCurso,
               SUM(CASE WHEN p.id IS NOT NULL THEN 1 ELSE 0 END) * 1.0 / COUNT(c.id) * 100 AS porcentajeAvance
        FROM Contenido c
        JOIN c.modulo m
        JOIN m.curso cu
        LEFT JOIN ProgresoContenido p ON c.id = p.contenido.id AND p.usuario.id = :usuarioId
        GROUP BY cu.id, cu.nombre
    """)
    List<ProgresoCursoProjection> obtenerProgresoPorUsuario(@Param("usuarioId") String usuarioId);

    @Query("""
        SELECT c.modulo.curso.id AS cursoId,
               c.modulo.curso.nombre AS nombreCurso,
               SUM(CASE WHEN p.id IS NOT NULL THEN 1 ELSE 0 END) * 1.0 / COUNT(c.id) * 100 AS porcentajeAvance
        FROM Contenido c
        JOIN c.modulo m
        JOIN m.curso cu
        LEFT JOIN ProgresoContenido p ON c.id = p.contenido.id AND p.usuario.id = :usuarioId
        WHERE cu.id = :cursoId
        GROUP BY cu.id, cu.nombre
    """)
    Optional<ProgresoCursoProjection> obtenerProgresoPorUsuarioYCurso(
            @Param("usuarioId") String usuarioId,
            @Param("cursoId") Long cursoId);
}
