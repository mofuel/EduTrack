package com.EduTrack.Controller;

import com.EduTrack.Model.AsistenciaDetalle;
import com.EduTrack.Service.AsistenciaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencia-detalle")
public class AsistenciaDetalleController {

    private final AsistenciaDetalleService asistenciaDetalleService;

    @Autowired
    public AsistenciaDetalleController(AsistenciaDetalleService asistenciaDetalleService) {
        this.asistenciaDetalleService = asistenciaDetalleService;
    }

    @GetMapping("/asistencia/{asistenciaId}")
    public List<AsistenciaDetalle> obtenerDetallesPorAsistencia(@PathVariable Long asistenciaId) {
        return asistenciaDetalleService.obtenerDetallesPorAsistencia(asistenciaId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDetalle> obtenerPorId(@PathVariable Long id) {
        Optional<AsistenciaDetalle> detalle = asistenciaDetalleService.obtenerPorId(id);
        return detalle.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AsistenciaDetalle crear(@RequestBody AsistenciaDetalle asistenciaDetalle) {
        return asistenciaDetalleService.guardar(asistenciaDetalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        asistenciaDetalleService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

