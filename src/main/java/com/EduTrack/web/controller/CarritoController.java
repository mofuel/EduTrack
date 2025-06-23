package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.CarritoDTO;
import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.domain.service.CarritoService;
import com.EduTrack.persistance.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private UsuariosRepository usuarioRepository;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<CarritoDTO>> listarCarrito(@PathVariable String usuarioId) {
        return ResponseEntity.ok(carritoService.listarCarritoPorUsuario(usuarioId));
    }

    @PostMapping("/agregar")
    public ResponseEntity<CarritoDTO> agregarCurso(@RequestBody CarritoDTO dto) {
        Optional<CarritoDTO> agregado = carritoService.agregarCursoAlCarrito(dto.getUsuarioId(), dto.getCursoId());
        return agregado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarCurso(@RequestParam Long cursoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // El "subject" del JWT (usualmente es el correo)

        Optional<Usuarios> usuario = usuarioRepository.getByEmail(email);
        if (usuario.isEmpty()) {
            return ResponseEntity.badRequest().build(); // O devolver UNAUTHORIZED
        }

        carritoService.eliminarCursoDelCarrito(usuario.get().getId(), cursoId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/existe")
    public ResponseEntity<Boolean> existeCurso(
            @RequestParam String usuarioId,
            @RequestParam Long cursoId) {
        boolean existe = carritoService.estaCursoEnCarrito(usuarioId, cursoId);
        return ResponseEntity.ok(existe);
    }
}
