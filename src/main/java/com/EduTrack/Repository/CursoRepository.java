package com.EduTrack.Repository;

import com.EduTrack.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CursoRepository extends JpaRepository<Curso, Long>{
    // agregar métodos personalizados si es necesario

}
