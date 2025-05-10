package com.EduTrack.persistance.repositoryimpl;


import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.persistance.crud.UsuariosCrudRepository;
import com.EduTrack.persistance.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuariosRepositoryImpl implements UsuariosRepository {

    @Autowired
    private UsuariosCrudRepository crud;

    @Override
    public List<Usuarios> getAll() {
        List<Usuarios> lista = new ArrayList<>();
        crud.findAll().forEach(lista::add);
        return lista;
    }


    @Override
    public Optional<Usuarios> getById(String id) {
        return crud.findById(id);
    }

    @Override
    public Optional<Usuarios> getByEmail(String email) {
        return Optional.ofNullable(crud.findByEmail(email));
    }

    @Override
    public Usuarios save(Usuarios usuario) {
        return crud.save(usuario);
    }

    @Override
    public void delete(String id) {
        crud.deleteById(id);
    }
}
