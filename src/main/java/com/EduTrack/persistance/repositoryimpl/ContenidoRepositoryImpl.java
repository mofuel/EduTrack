package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.domain.repository.ContenidoRepository;
import com.EduTrack.persistance.crud.ContenidoCrudRepository;
import com.EduTrack.persistance.entity.Contenido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContenidoRepositoryImpl implements ContenidoRepository {

    @Autowired
    private ContenidoCrudRepository crud;

    @Override
    public List<Contenido> getByModuloId(Long moduloId) {
        return crud.findByModuloId(moduloId);
    }

    @Override
    public Optional<Contenido> getById(Long id) {
        return crud.findById(id);
    }

    @Override
    public Contenido save(Contenido contenido) {
        return crud.save(contenido);
    }

    @Override
    public void delete(Long id) {
        crud.deleteById(id);
    }
}
