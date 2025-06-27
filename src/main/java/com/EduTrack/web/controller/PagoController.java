package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.PagoDTO;
import com.EduTrack.domain.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // POST: Registrar un nuevo pago
    @PostMapping("/registrar")
    public ResponseEntity<PagoDTO> registrarPago(@RequestBody PagoDTO pagoDTO) {
        Optional<PagoDTO> registrado = pagoService.registrarPago(pagoDTO);
        return registrado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // GET: Verificar si ya fue pagado un curso por un usuario
    @GetMapping("/existe")
    public ResponseEntity<Boolean> yaFuePagado(
            @RequestParam String usuarioId,
            @RequestParam Long cursoId) {
        boolean existe = pagoService.yaFuePagado(usuarioId, cursoId);
        return ResponseEntity.ok(existe);
    }

    // GET: Listar todos los pagos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PagoDTO>> listarPorUsuario(@PathVariable String usuarioId) {
        return ResponseEntity.ok(pagoService.listarPagosPorUsuario(usuarioId));
    }

    // GET: Listar todos los pagos de un curso
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<PagoDTO>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(pagoService.listarPagosPorCurso(cursoId));
    }
}
