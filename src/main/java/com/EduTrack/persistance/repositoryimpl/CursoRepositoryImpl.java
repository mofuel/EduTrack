package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.persistance.crud.CursoCrudRepository;
import com.EduTrack.persistance.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    @Autowired
    private CursoCrudRepository crud;

    @Override
    public List<Curso> getAll() {
        return crud.findByActivoTrue();
    }

    @Override
    public Optional<Curso> getById(Long id) {
        return crud.findById(id)
                .filter(Curso::getActivo);
    }

    @Override
    public Curso save(Curso curso) {
        return crud.save(curso);
    }

    @Override
    public void softDelete(Long id) {
        crud.findById(id).ifPresent(curso -> {
            curso.setActivo(false); // Lo pone como inactivo
            crud.save(curso);
        });
    }

    @Override
    public List<Curso> getByDocenteId(String docenteId) {
        return crud.findByDocente_IdAndActivoTrue(docenteId);
    }

    @Override
    public List<Curso> getByEstudianteId(String estudianteId) {
        return crud.findByEstudiantes_IdAndActivoTrue(estudianteId);
    }

    @Override
    public List<Curso> searchByNombre(String nombre) {
        return crud.findByNombreContainingIgnoreCaseAndActivoTrue(nombre);
    }
}
