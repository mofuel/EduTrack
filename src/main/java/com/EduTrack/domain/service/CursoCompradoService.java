package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.CursoCompradoDTO;
import com.EduTrack.domain.repository.CursoCompradoRepository;
import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.persistance.entity.Curso;
import com.EduTrack.persistance.entity.CursoComprado;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.persistance.mapper.CursoCompradoMapper;
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

    public List<CursoCompradoDTO> listarCursosComprados(String usuarioId) {
        return mapper.toDTOList(cursoCompradoRepository.findByUsuarioId(usuarioId));
    }

    public Optional<CursoCompradoDTO> registrarCompra(String usuarioId, Long cursoId) {
        Optional<Usuarios> usuarioOpt = usuarioRepository.getById(usuarioId);
        Optional<Curso> cursoOpt = cursoRepository.getById(cursoId);

        if (usuarioOpt.isEmpty() || cursoOpt.isEmpty()) return Optional.empty();

        CursoComprado comprado = new CursoComprado();
        comprado.setUsuario(usuarioOpt.get());
        comprado.setCurso(cursoOpt.get());

        CursoComprado guardado = cursoCompradoRepository.save(comprado);
        return Optional.of(mapper.toDTO(guardado));
    }

    public boolean yaFueComprado(String usuarioId, Long cursoId) {
        return cursoCompradoRepository.existeCompra(usuarioId, cursoId);
    }
}
