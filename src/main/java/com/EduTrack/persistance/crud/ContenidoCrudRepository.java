package com.EduTrack.persistance.crud;
import com.EduTrack.persistance.entity.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContenidoCrudRepository extends JpaRepository<Contenido, Long> {
    List<Contenido> findByModuloId(Long moduloId);
}
