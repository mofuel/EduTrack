package com.EduTrack.persistance.crud;

import com.EduTrack.persistance.entity.Usuarios;
import org.springframework.data.repository.CrudRepository;

public interface UsuariosCrudRepository extends CrudRepository<Usuarios, String> {

    Usuarios findByEmail(String email);

}
