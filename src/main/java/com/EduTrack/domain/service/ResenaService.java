package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.ResenaDTO;
import com.EduTrack.domain.repository.IResenaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaService {

    private final IResenaRepository repository;

    public void guardar(ResenaDTO dto, Long idCurso, String username) {
        repository.guardar(dto, idCurso, username);
    }

    public void guardarPorEmail(ResenaDTO dto, Long idCurso, String emailUsuario) {
        repository.guardarPorEmail(dto, idCurso, emailUsuario);
    }

    public List<ResenaDTO> listar() {
        return repository.listar();
    }

    public List<ResenaDTO> listarPorCurso(Long idCurso) {
        return repository.listarPorCurso(idCurso);
    }
}
