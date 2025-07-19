package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.ProgresoContenidoDTO;
import com.EduTrack.domain.dto.ProgresoCursoDTO;
import com.EduTrack.domain.service.ProgresoContenidoService;
import com.EduTrack.domain.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progreso")
public class ProgresoContenidoController {

    @Autowired
    private ProgresoContenidoService progresoService;

    @Autowired
    private UsuariosService usuariosService;

    // Obtener el progreso de un usuario por su ID
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ProgresoContenidoDTO>> obtenerPorUsuario(@PathVariable String usuarioId) {
        return ResponseEntity.ok(progresoService.obtenerProgresosPorUsuario(usuarioId));
    }

    // Verifica si ya se visualizó un contenido
    @GetMapping("/existe")
    public ResponseEntity<Boolean> existeVisualizacion(
            @RequestParam String usuarioId,
            @RequestParam Long contenidoId
    ) {
        boolean existe = progresoService.existeProgreso(usuarioId, contenidoId);
        return ResponseEntity.ok(existe);
    }

    // Guardar un nuevo registro de progreso (al visualizar un contenido)

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody ProgresoContenidoDTO progresoDTO) {
        try {
            ProgresoContenidoDTO guardado = progresoService.guardar(progresoDTO);
            return ResponseEntity.status(guardado.getId() == null ? HttpStatus.OK : HttpStatus.CREATED).body(guardado);
        } catch (RuntimeException e) {
            System.out.println("⚠️ Progreso ya registrado o no recuperado. Ignorando.");
            return ResponseEntity.status(HttpStatus.OK).body("Ya registrado");
        }
    }




    // Obtener un progreso específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ProgresoContenidoDTO> obtenerPorId(@PathVariable Long id) {
        return progresoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}/avance-cursos")
    public ResponseEntity<List<ProgresoCursoDTO>> obtenerAvanceCursos(@PathVariable String usuarioId) {
        return ResponseEntity.ok(progresoService.obtenerAvanceCursosPorUsuario(usuarioId));
    }

    @GetMapping("/curso/{cursoId}/usuario/{usuarioId}")
    public ResponseEntity<ProgresoCursoDTO> obtenerAvancePorCursoYUsuario(
            @PathVariable Long cursoId,
            @PathVariable String usuarioId) {
        return progresoService.obtenerAvancePorUsuarioYCurso(usuarioId, cursoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id-por-email")
    public ResponseEntity<String> obtenerIdPorEmail(@RequestParam String email) {
        return usuariosService.buscarPorEmail(email)
                .map(usuario -> usuario.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
