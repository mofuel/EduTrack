package com.EduTrack.domain.service;

import com.EduTrack.persistance.entity.Avance;
import com.EduTrack.domain.repository.AvanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvanceService {
    @Autowired
    private AvanceRepository avanceRepository;

    public List<Avance> obtenerAvancesPorUsuario(Long usuarioId) {
        return avanceRepository.findByUsuarioId(usuarioId);
    }

    public Avance registrarAvance(Avance avance) {
        return avanceRepository.save(avance);
    }
}
