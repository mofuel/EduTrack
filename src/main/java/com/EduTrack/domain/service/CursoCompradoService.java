package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.CursoCompradoDTO;
import com.EduTrack.domain.repository.CursoCompradoRepository;
import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.persistence.entity.Curso;
import com.EduTrack.persistence.entity.CursoComprado;
import com.EduTrack.persistence.entity.Usuarios;
import com.EduTrack.persistence.mapper.CursoCompradoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoCompradoService {

    @Autowired
    private CursoCompradoRepository cursoCompradoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private CursoCompradoMapper mapper;

    public List<CursoCompradoDTO> listarCursosComprados(Long usuarioId) {
        return mapper.toDTOList(cursoCompradoRepository.findByUsuarioId(usuarioId));
    }



    public boolean yaFueComprado(Long usuarioId, Long cursoId) {
        return cursoCompradoRepository.existeCompra(usuarioId, cursoId);
    }
}