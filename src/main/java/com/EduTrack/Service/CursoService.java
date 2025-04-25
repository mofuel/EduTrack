package com.EduTrack.Service;

import com.EduTrack.Model.Curso;
import com.EduTrack.Model.Usuarios;
import com.EduTrack.Repository.CursoRepository;
import com.EduTrack.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    // Metodo para crear un curso
    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Metodo para agregar estudiantes a un curso
    public Curso agregarEstudiantesACurso(Long cursoId, List<String> estudiantesIds) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            for (String estudianteId : estudiantesIds) {
                Optional<Usuarios> estudiante = usuariosRepository.findById(estudianteId);
                estudiante.ifPresent(curso.getEstudiantes()::add);  // Añadir estudiante a la lista
            }
            return cursoRepository.save(curso);  // Guardar cambios
        }
        return null;
    }

    // Metodo para obtener todos los cursos
    public List<Curso> obtenerCursos() {
        return cursoRepository.findAll();  // Devuelve todos los cursos desde la base de datos
    }

    // Metodo para obtener un curso por ID
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    // Metodo para eliminar un curso
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }



}
