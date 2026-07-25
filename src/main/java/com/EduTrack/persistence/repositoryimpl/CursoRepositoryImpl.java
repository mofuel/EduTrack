package com.EduTrack.persistence.repositoryimpl;

import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.persistence.crud.CursoCompradoCrudRepository;
import com.EduTrack.persistence.crud.CursoCrudRepository;
import com.EduTrack.persistence.entity.Curso;
import com.EduTrack.persistence.entity.CursoComprado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    @Autowired
    private CursoCrudRepository crud;

    @Autowired
    private CursoCompradoCrudRepository cursoCompradoCrud;



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
    public List<Curso> getByDocenteId(Long docenteId) {
        return crud.findByDocente_IdAndActivoTrue(docenteId);
    }

    @Override
    public List<Curso> getByEstudianteId(Long estudianteId) {
        return cursoCompradoCrud.findByUsuario_Id(estudianteId)
                .stream()
                .map(CursoComprado::getCurso)
                .filter(Curso::getActivo)
                .toList();
    }


    @Override
    public List<Curso> searchByNombre(String nombre) {
        return crud.findByNombreContainingIgnoreCaseAndActivoTrue(nombre);
    }

    @Override
    public List<Curso> getDisponiblesParaCompra() {
        return crud.findByActivoTrueAndDisponibleParaCompraTrue();
    }

    @Override
    public List<Curso> searchDisponiblesPorNombre(String nombre) {
        return crud.findByNombreContainingIgnoreCaseAndActivoTrueAndDisponibleParaCompraTrue(nombre);
    }
}
