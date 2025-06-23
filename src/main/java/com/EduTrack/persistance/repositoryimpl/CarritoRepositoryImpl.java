package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.domain.repository.CarritoRepository;
import com.EduTrack.persistance.crud.CarritoCrudRepository;
import com.EduTrack.persistance.entity.Carrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarritoRepositoryImpl implements CarritoRepository {

    @Autowired
    private CarritoCrudRepository crud;

    @Override
    public List<Carrito> getByUsuarioId(String usuarioId) {
        return crud.findByUsuario_Id(usuarioId);
    }

    @Override
    public Optional<Carrito> getByUsuarioIdAndCursoId(String usuarioId, Long cursoId) {
        return crud.findByUsuario_IdAndCurso_Id(usuarioId, cursoId);
    }

    @Override
    public Carrito save(Carrito carrito) {
        return crud.save(carrito);
    }

    @Override
    public void deleteByUsuarioIdAndCursoId(String usuarioId, Long cursoId) {
        crud.deleteByUsuario_IdAndCurso_Id(usuarioId, cursoId);
    }
}
