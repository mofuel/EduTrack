package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.ResenaDTO;
import com.EduTrack.domain.service.ResenaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ResenaController {

    private final ResenaService service;

    @PostMapping("/{idCurso}")
    public ResponseEntity<Void> guardar(@PathVariable Long idCurso, @Valid @RequestBody ResenaDTO dto, Authentication authentication) {
        String emailUsuario = authentication.getName();
        service.guardarPorEmail(dto, idCurso, emailUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<ResenaDTO>> listarPorCurso(@PathVariable Long idCurso) {
        return ResponseEntity.ok(service.listarPorCurso(idCurso));
    }


}
