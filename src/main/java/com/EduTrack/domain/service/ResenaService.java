package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.ResenaDTO;
import com.EduTrack.domain.repository.CursoCompradoRepository;
import com.EduTrack.domain.repository.IResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private IResenaRepository resenaRepository;

    @Autowired
    private CursoCompradoRepository cursoCompradoRepository;

    public ResenaDTO crearResena(ResenaDTO dto, Long usuarioId) {
        if (!cursoCompradoRepository.existeCompra(usuarioId, dto.getCursoId())) {
            throw new IllegalArgumentException("No puedes reseñar un curso que no has comprado");
        }
        if (dto.getEstrellas() < 1 || dto.getEstrellas() > 5) {
            throw new IllegalArgumentException("Las estrellas deben estar entre 1 y 5");
        }
        return resenaRepository.guardar(dto, dto.getCursoId(), usuarioId);
    }

    public List<ResenaDTO> listarPorCurso(Long cursoId) {
        return resenaRepository.listarPorCurso(cursoId);
    }

    public List<ResenaDTO> listarTodas() {
        return resenaRepository.listar();
    }
}