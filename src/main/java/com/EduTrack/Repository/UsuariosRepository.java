package com.EduTrack.Repository;

import com.EduTrack.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, String> {

    // Agregar consultas personalizadas
    Usuarios findByEmail(String email);

}
