package com.EduTrack.domain.service;

import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.persistance.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;


    // Obtener todos los cursos
    public List<Curso> listarCursos() {
        return cursoRepository.getAll();
    }

    // Obtener un curso por su ID
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.getById(id);
    }


    // Crear o actualizar un curso
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


    // Eliminar un curso
    public void eliminarCurso(Long id) {
        cursoRepository.delete(id);
    }

}
