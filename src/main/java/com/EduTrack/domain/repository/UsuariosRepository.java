package com.EduTrack.domain.repository;

import com.EduTrack.persistance.entity.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuariosRepository {

    List<Usuarios> getAll();
    Optional<Usuarios> getById(String id);
    Optional<Usuarios> getByEmail(String email);
    Usuarios save(Usuarios usuario);
    void delete(String id);
}
