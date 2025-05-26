package com.EduTrack.persistance.crud;

import com.EduTrack.persistance.entity.Usuarios;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuariosCrudRepository extends CrudRepository<Usuarios, String> {

    Usuarios findByEmail(String email);

    Usuarios findByDni(String dni);

    Usuarios findByTelefono(String telefono);

    @Query("SELECT u.id FROM Usuarios u ORDER BY u.id DESC")
    List<String> findAllIdsDesc(Pageable pageable);

}
