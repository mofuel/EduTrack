package com.EduTrack.domain.repository;

import com.EduTrack.domain.dto.ResenaDTO;
import java.util.List;

public interface IResenaRepository {
    ResenaDTO guardar(ResenaDTO dto, Long idCurso, Long idUsuario);
    void guardarPorEmail(ResenaDTO dto, Long idCurso, String emailUsuario);
    List<ResenaDTO> listar();
    List<ResenaDTO> listarPorCurso(Long idCurso);
}