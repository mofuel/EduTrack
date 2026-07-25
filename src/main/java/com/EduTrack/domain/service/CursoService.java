package com.EduTrack.domain.service;

import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.persistence.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional(readOnly = true)
    public List<Curso> listarCursos() {
        return cursoRepository.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.getById(id);
    }

    @Transactional(readOnly = true)
    public List<Curso> listarCursosPorDocente(Long docenteId) {
        return cursoRepository.getByDocenteId(docenteId);
    }

    @Transactional(readOnly = true)
    public List<Curso> listarCursosPorEstudiante(Long estudianteId) {
        return cursoRepository.getByEstudianteId(estudianteId);
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarCursosPorNombre(String nombre) {
        return cursoRepository.searchByNombre(nombre);
    }

    @Transactional
    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        Optional<Curso> cursoExistente = cursoRepository.getById(id);
        if (cursoExistente.isPresent()) {
            cursoActualizado.setId(id);
            return cursoRepository.save(cursoActualizado);
        }
        return null;
    }

    @Transactional
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

    @Transactional(readOnly = true)
    public List<Curso> listarCursosDisponiblesParaCompra() {
        return cursoRepository.getDisponiblesParaCompra();
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarCursosDisponiblesPorNombre(String nombre) {
        return cursoRepository.searchDisponiblesPorNombre(nombre);
    }

    @Transactional
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

    @Transactional(readOnly = true)
    public Page<Curso> listarCursos(Pageable pageable) {
        return cursoRepository.getAll(pageable);
    }
}