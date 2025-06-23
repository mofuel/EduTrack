package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.CursoDTO;
import com.EduTrack.domain.service.CursoService;
import com.EduTrack.persistance.entity.Curso;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.persistance.mapper.CursoMapper;
import com.EduTrack.domain.repository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private UsuariosRepository usuarioRepository;

    // GET: Listar todos los cursos
    @GetMapping
    public List<CursoDTO> obtenerCursos() {
        return cursoService.listarCursos()
                .stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET: Obtener curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> obtenerCursoPorId(@PathVariable Long id) {
        return cursoService.obtenerCursoPorId(id)
                .map(curso -> ResponseEntity.ok(cursoMapper.toDTO(curso)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crear nuevo curso
    @PostMapping
    public ResponseEntity<?> crearCurso(@RequestBody CursoDTO dto) {
        // Obtener el docente autenticado desde el token JWT
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  // Esto obtiene el subject del token JWT (email)
        Optional<Usuarios> docente = usuarioRepository.getByEmail(email);

        if (docente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Docente no encontrado");
        }

        // Obtener los estudiantes por sus IDs (si los hay)
        List<Usuarios> estudiantes = dto.getEstudiantesIds() != null
                ? dto.getEstudiantesIds().stream()
                .map(usuarioRepository::getById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList())
                : List.of();

        // Crear y guardar el curso
        Curso curso = cursoMapper.toEntity(dto, docente.get(), estudiantes);
        Curso cursoGuardado = cursoService.guardarCurso(curso);
        CursoDTO resultado = cursoMapper.toDTO(cursoGuardado);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }


    // PUT: Actualizar curso
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCurso(@PathVariable Long id, @RequestBody CursoDTO dto) {
        Optional<Curso> existente = cursoService.obtenerCursoPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Curso curso = existente.get();
        cursoMapper.updateEntityFromDTO(dto, curso);
        return ResponseEntity.ok(cursoMapper.toDTO(cursoService.guardarCurso(curso)));
    }

    // DELETE: Eliminar curso (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id) {
        Optional<Curso> cursoOpt = cursoService.obtenerCursoPorId(id);
        if (cursoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
        }

        cursoService.eliminarCurso(id); // Soft delete (activo = false)
        return ResponseEntity.ok("Curso eliminado correctamente");
    }


    // GET: Obtener cursos por ID de docente
    @GetMapping("/docente/{docenteId}")
    public List<CursoDTO> obtenerPorDocente(@PathVariable String docenteId) {
        return cursoService.listarCursosPorDocente(docenteId)
                .stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET: Obtener cursos por ID de estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public List<CursoDTO> obtenerPorEstudiante(@PathVariable String estudianteId) {
        return cursoService.listarCursosPorEstudiante(estudianteId)
                .stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public String getEmailFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // Este es el "subject" del JWT, usualmente el email
    }

    // GET: Obtener todos los cursos disponibles para compra
    @GetMapping("/disponibles")
    public List<CursoDTO> obtenerCursosDisponiblesParaCompra() {
        return cursoService.listarCursosDisponiblesParaCompra()
                .stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET: Buscar cursos disponibles por nombre
    @GetMapping("/disponibles/buscar")
    public List<CursoDTO> buscarCursosDisponiblesPorNombre(@RequestParam("nombre") String nombre) {
        return cursoService.buscarCursosDisponiblesPorNombre(nombre)
                .stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // PATCH: Cambiar disponibilidad de compra
    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<?> cambiarDisponibilidadCompra(@PathVariable Long id, @RequestParam boolean disponible) {
        boolean actualizado = cursoService.actualizarDisponibilidadCompra(id, disponible);
        if (actualizado) {
            return ResponseEntity.ok("Disponibilidad de compra actualizada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
        }
    }

}
