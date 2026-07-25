package com.EduTrack.domain.service;

import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.persistence.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.getAll().stream()
                .filter(Curso::getActivo)
                .toList();
    }

    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.getById(id);
    }

    public List<Curso> listarCursosPorDocente(Long docenteId) {
        return cursoRepository.getByDocenteId(docenteId);
    }

    public List<Curso> listarCursosPorEstudiante(Long estudianteId) {
        return cursoRepository.getByEstudianteId(estudianteId);
    }

    public List<Curso> buscarCursosPorNombre(String nombre) {
        return cursoRepository.searchByNombre(nombre);
    }

    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        Optional<Curso> cursoExistente = cursoRepository.getById(id);
        if (cursoExistente.isPresent()) {
            cursoActualizado.setId(id);
            return cursoRepository.save(cursoActualizado);
        }
        return null;
    }

    public boolean eliminarCurso(Long id) {
        Optional<Curso> cursoOpt = cursoRepository.getById(id);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            curso.setActivo(false);
            cursoRepository.save(curso);
            return true;
        }
        return false;
    }

    public List<Curso> listarCursosDisponiblesParaCompra() {
        return cursoRepository.getDisponiblesParaCompra();
    }

    public List<Curso> buscarCursosDisponiblesPorNombre(String nombre) {
        return cursoRepository.searchDisponiblesPorNombre(nombre);
    }

    public boolean actualizarDisponibilidadCompra(Long cursoId, boolean disponible) {
        Optional<Curso> cursoOpt = cursoRepository.getById(cursoId);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            curso.setDisponibleParaCompra(disponible);
            cursoRepository.save(curso);
            return true;
        }
        return false;
    }
}