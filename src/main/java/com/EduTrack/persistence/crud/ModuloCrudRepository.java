package com.EduTrack.persistence.crud;

import com.EduTrack.persistence.entity.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ModuloCrudRepository extends JpaRepository<Modulo, Long>  {
    List<Modulo> findByCursoId(Long cursoId);
}
