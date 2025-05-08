package com.EduTrack.Repository;

import com.EduTrack.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

//El JpaRepository nos proporciona las operaciones CRUD basicas
public interface UsuariosRepository extends JpaRepository<Usuarios, String> {

    // Agregar consultas personalizadas
    Usuarios findByEmail(String email);

}
