package com.EduTrack.domain.repository;

import com.EduTrack.persistence.entity.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UsuariosRepository {

    List<Usuarios> getAll();
    Optional<Usuarios> getById(Long id);
    Optional<Usuarios> getByEmail(String email);
    Optional<Usuarios> getByDni(String dni);
    Optional<Usuarios> getByTelefono(String telefono);
    Usuarios save(Usuarios usuario);
    void delete(Long id);
    Page<Usuarios> getAll(Pageable pageable);
}
