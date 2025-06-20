package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.ContenidoDTO;
import com.EduTrack.domain.service.ContenidoService;
import com.EduTrack.persistance.entity.Contenido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoController {

    @Autowired
    private ContenidoService contenidoService;

    @GetMapping("/modulo/{moduloId}")
    public ResponseEntity<List<ContenidoDTO>> listarPorModulo(@PathVariable Long moduloId) {
        return ResponseEntity.ok(contenidoService.listarPorModuloDTO(moduloId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContenidoDTO> obtenerPorId(@PathVariable Long id) {
        Optional<ContenidoDTO> contenidoOpt = contenidoService.obtenerDTOPorId(id);
        return contenidoOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContenidoDTO> crear(@RequestBody ContenidoDTO dto) {
        ContenidoDTO guardado = contenidoService.guardarDesdeDTO(dto);
        return ResponseEntity.ok(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContenidoDTO> actualizar(@PathVariable Long id, @RequestBody ContenidoDTO dto) {
        dto.setId(id); // Asegurar que el ID sea el correcto
        ContenidoDTO actualizado = contenidoService.guardarDesdeDTO(dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = contenidoService.eliminarContenido(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
