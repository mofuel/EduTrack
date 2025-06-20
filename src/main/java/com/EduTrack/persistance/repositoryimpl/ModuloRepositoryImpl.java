package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.domain.repository.ModuloRepository;
import com.EduTrack.persistance.crud.ModuloCrudRepository;
import com.EduTrack.persistance.entity.Modulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ModuloRepositoryImpl implements ModuloRepository {

    @Autowired
    private ModuloCrudRepository crud;

    @Override
    public List<Modulo> getByCursoId(Long cursoId) {
        return crud.findByCursoId(cursoId);
    }

    @Override
    public Optional<Modulo> getById(Long id) {
        return crud.findById(id);
    }

    @Override
    public Modulo save(Modulo modulo) {
        return crud.save(modulo);
    }

    @Override
    public void delete(Long id) {
        crud.deleteById(id);
    }
}
