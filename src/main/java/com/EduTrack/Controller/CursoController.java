package com.EduTrack.Controller;

import com.EduTrack.Model.Curso;
import com.EduTrack.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.crearCurso(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> obtenerCursos() {
        List<Curso> cursos = cursoService.obtenerCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    // Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.obtenerCursoPorId(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Agregar estudiantes a un curso
    @PostMapping("/{cursoId}/estudiantes")
    public ResponseEntity<Curso> agregarEstudiantesACurso(@PathVariable Long cursoId, @RequestBody List<String> estudiantesIds) {
        Curso cursoActualizado = cursoService.agregarEstudiantesACurso(cursoId, estudiantesIds);
        if (cursoActualizado != null) {
            return new ResponseEntity<>(cursoActualizado, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();  // Si no se encuentra el curso
    }

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }



}
