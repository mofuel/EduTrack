package com.EduTrack.Service;

import com.EduTrack.Model.Curso;
import com.EduTrack.Repository.CursoRepository;
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
        return cursoRepository.findAll();
    }

    // Obtener un curso por su ID
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    // Crear o actualizar un curso
    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Eliminar un curso
    public void eliminarCurso(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Curso no encontrado con ID: " + id);
        }
    }
}
