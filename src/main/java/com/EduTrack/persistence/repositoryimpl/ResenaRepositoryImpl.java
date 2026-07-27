package com.EduTrack.persistence.repositoryimpl;

import java.util.List;
import com.EduTrack.domain.dto.ResenaDTO;
import com.EduTrack.domain.repository.IResenaRepository;
import com.EduTrack.persistence.crud.CursoCrudRepository;
import com.EduTrack.persistence.crud.ResenaCrudRepository;
import com.EduTrack.persistence.crud.UsuariosCrudRepository;
import com.EduTrack.persistence.entity.Curso;
import com.EduTrack.persistence.entity.Resena;
import com.EduTrack.persistence.entity.Usuarios;
import com.EduTrack.persistence.mapper.ResenaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ResenaRepositoryImpl implements IResenaRepository {

    @Autowired
    private ResenaCrudRepository crud;

    @Autowired
    private ResenaMapper mapper;

    @Autowired
    private UsuariosCrudRepository usuarioCrudRepository;

    @Autowired
    private CursoCrudRepository cursoCrudRepository;

    @Override
    public ResenaDTO guardar(ResenaDTO dto, Long idCurso, Long idUsuario) {
        Resena resena = mapper.toResena(dto);
        Curso curso = cursoCrudRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Usuarios usuario = usuarioCrudRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        resena.setCurso(curso);
        resena.setUsuarios(usuario);
        crud.save(resena);
        return mapper.toResenaDTO(resena);
    }

    @Override
    public void guardarPorEmail(ResenaDTO dto, Long idCurso, String emailUsuario) {
        Resena resena = mapper.toResena(dto);

        Curso curso = cursoCrudRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Usuarios usuario = usuarioCrudRepository.findByEmail(emailUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        resena.setCurso(curso);
        resena.setUsuarios(usuario);

        crud.save(resena);
    }

    @Override
    public List<ResenaDTO> listar() {
        List<Resena> resenas = StreamSupport
                .stream(crud.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return mapper.toResenaDTOList(resenas);
    }

    @Override
    public List<ResenaDTO> listarPorCurso(Long idCurso) {
        List<Resena> resenas = StreamSupport
                .stream(crud.findByCursoId(idCurso).spliterator(), false)
                .collect(Collectors.toList());

        return mapper.toResenaDTOList(resenas);
    }
}