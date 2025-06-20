package com.EduTrack.domain.repository;

import com.EduTrack.persistance.entity.Modulo;
import java.util.List;
import java.util.Optional;

public interface ModuloRepository {

    List<Modulo> getByCursoId(Long cursoId);
    Optional<Modulo> getById(Long id);
    Modulo save(Modulo modulo);
    void delete(Long id);
}
