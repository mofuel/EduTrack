package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.domain.repository.CursoCompradoRepository;
import com.EduTrack.persistance.crud.CursoCompradoCrudRepository;
import com.EduTrack.persistance.entity.CursoComprado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoCompradoRepositoryImpl implements CursoCompradoRepository {

    @Autowired
    private CursoCompradoCrudRepository crud;

    @Override
    public List<CursoComprado> findByUsuarioId(String usuarioId) {
        return crud.findByUsuario_Id(usuarioId);
    }

    @Override
    public boolean existeCompra(String usuarioId, Long cursoId) {
        return crud.existsByUsuario_IdAndCurso_Id(usuarioId, cursoId);
    }

    @Override
    public CursoComprado save(CursoComprado cursoComprado) {
        return crud.save(cursoComprado);
    }

    @Override
    public Optional<CursoComprado> findById(Long id) {
        return crud.findById(id);
    }
}
