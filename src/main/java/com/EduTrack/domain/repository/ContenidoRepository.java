package com.EduTrack.domain.repository;

import com.EduTrack.persistence.entity.Contenido;
import java.util.List;
import java.util.Optional;

public interface ContenidoRepository {

    List<Contenido> getByModuloId(Long moduloId);
    Optional<Contenido> getById(Long id);
    Contenido save(Contenido contenido);
    void delete(Long id);
}
