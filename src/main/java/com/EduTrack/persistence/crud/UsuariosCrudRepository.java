package com.EduTrack.persistence.crud;

import com.EduTrack.persistence.entity.Usuarios;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuariosCrudRepository extends CrudRepository<Usuarios, Long> {

    Usuarios findByEmail(String email);

    Usuarios findByDni(String dni);

    Usuarios findByTelefono(String telefono);

    @Query("SELECT u.id FROM Usuarios u ORDER BY u.id DESC")
    List<Long> findAllIdsDesc(Pageable pageable);

}
