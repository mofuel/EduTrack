package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.CursoCompradoDTO;
import com.EduTrack.domain.service.CursoCompradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos-comprados")
public class CursoCompradoController {

    @Autowired
    private CursoCompradoService service;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<CursoCompradoDTO>> listarComprados(@PathVariable String usuarioId) {
        return ResponseEntity.ok(service.listarCursosComprados(usuarioId));
    }

    @PostMapping("/comprar")
    public ResponseEntity<CursoCompradoDTO> comprarCurso(
            @RequestParam String usuarioId,
            @RequestParam Long cursoId) {
        Optional<CursoCompradoDTO> comprado = service.registrarCompra(usuarioId, cursoId);
        return comprado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> yaComprado(
            @RequestParam String usuarioId,
            @RequestParam Long cursoId) {
        boolean comprado = service.yaFueComprado(usuarioId, cursoId);
        return ResponseEntity.ok(comprado);
    }
}
