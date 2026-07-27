package com.EduTrack.persistence.crud;

import com.EduTrack.persistence.entity.Resena;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResenaCrudRepository extends CrudRepository<Resena, Long> {
    List<Resena> findByCursoId(Long cursoId);
}