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

    // Obtener todos los cursos activos
    public List<Curso> listarCursos() {
        return cursoRepository.getAll().stream()
                .filter(Curso::getActivo) // Solo cursos activos
                .toList();
    }

    // Obtener un curso por su ID
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.getById(id);
    }

    // Obtener cursos por docente ID
    public List<Curso> listarCursosPorDocente(String docenteId) {
        return cursoRepository.getByDocenteId(docenteId);
    }

    // Obtener cursos por estudiante ID
    public List<Curso> listarCursosPorEstudiante(String estudianteId) {
        return cursoRepository.getByEstudianteId(estudianteId);
    }

    // Buscar cursos por nombre
    public List<Curso> buscarCursosPorNombre(String nombre) {
        return cursoRepository.searchByNombre(nombre);
    }

    // Crear un nuevo curso
    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Actualizar curso existente
    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        Optional<Curso> cursoExistente = cursoRepository.getById(id);
        if (cursoExistente.isPresent()) {
            cursoActualizado.setId(id);
            return cursoRepository.save(cursoActualizado);
        }
        return null;
    }

    // Eliminar un curso (soft delete)
    public boolean eliminarCurso(Long id) {
        Optional<Curso> cursoOpt = cursoRepository.getById(id);
        if (cursoOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            curso.setActivo(false); // 👈 Desactivar curso
            cursoRepository.save(curso); // Guardar el cambio
            return true;
        }
        return false;
    }

    // Listar cursos activos y disponibles para compra
    public List<Curso> listarCursosDisponiblesParaCompra() {
        return cursoRepository.getDisponiblesParaCompra();
    }

    // Buscar cursos disponibles para compra por nombre
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
