package com.EduTrack.persistance.crud;

import com.EduTrack.persistance.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoCrudRepository extends JpaRepository<Curso, Long> {
    // Añadir métodos personalizados aquí
}
