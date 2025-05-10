package com.EduTrack.domain.repository;

import com.EduTrack.persistance.entity.Avance;
import java.util.List;

public interface AvanceRepository {
    List<Avance> findByUsuarioId(Long usuarioId);
    Avance save(Avance avance);
}
