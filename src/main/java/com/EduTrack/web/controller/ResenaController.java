package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.ResenaDTO;
import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.domain.service.ResenaService;
import com.EduTrack.persistence.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @Autowired
    private UsuariosRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> crearResena(@RequestBody ResenaDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Optional<Usuarios> usuario = usuarioRepository.getByEmail(email);
        if (usuario.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        ResenaDTO creada = resenaService.crearResena(dto, usuario.get().getId());
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<ResenaDTO>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(resenaService.listarPorCurso(cursoId));
    }

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> listarTodas() {
        return ResponseEntity.ok(resenaService.listarTodas());
    }
}
