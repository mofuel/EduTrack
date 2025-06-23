package com.EduTrack.persistance.crud;

import com.EduTrack.persistance.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoCrudRepository extends JpaRepository<Carrito, Long> {

    // Buscar todos los cursos en el carrito de un usuario
    List<Carrito> findByUsuario_Id(String usuarioId);

    // Verificar si un curso ya está en el carrito del usuario (para evitar duplicados)
    Optional<Carrito> findByUsuario_IdAndCurso_Id(String usuarioId, Long cursoId);

    // Eliminar un curso específico del carrito de un usuario
    void deleteByUsuario_IdAndCurso_Id(String usuarioId, Long cursoId);
}
