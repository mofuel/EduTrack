package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.persistance.crud.CursoCrudRepository;
import com.EduTrack.persistance.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    @Autowired
    private CursoCrudRepository crud;

    @Override
    public List<Curso> getAll() {
        return crud.findAll();
    }

    @Override
    public Optional<Curso> getById(Long id) {
        return crud.findById(id);
    }

    @Override
    public Curso save(Curso curso) {
        return crud.save(curso);
    }

    @Override
    public void delete(Long id) {
        crud.deleteById(id);
    }
}
