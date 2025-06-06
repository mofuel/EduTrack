package com.EduTrack.persistance.repositoryimpl;


import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.persistance.crud.UsuariosCrudRepository;
import com.EduTrack.persistance.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public Optional<Usuarios> getByDni(String dni) {
        return Optional.ofNullable(crud.findByDni(dni));
    }

    @Override
    public Optional<Usuarios> getByTelefono(String telefono) {
        return Optional.ofNullable(crud.findByTelefono(telefono));
    }

    @Override
    public Usuarios save(Usuarios usuario) {
        return crud.save(usuario);
    }

    @Override
    public void delete(String id) {
        crud.deleteById(id);
    }

    // Metodo nuevo para obtener el último ID
    public Optional<String> findLastId() {
        List<String> ids = crud.findAllIdsDesc(PageRequest.of(0, 1));
        if (ids.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ids.get(0));
    }
}
