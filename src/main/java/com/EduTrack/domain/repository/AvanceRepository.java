package com.EduTrack.domain.repository;

import com.EduTrack.persistence.entity.Avance;
import java.util.List;

public interface AvanceRepository {
    List<Avance> findByUsuarioId(String usuarioId);
    Avance save(Avance avance);
}
