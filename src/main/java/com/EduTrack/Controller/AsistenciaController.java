package com.EduTrack.Controller;

import com.EduTrack.Model.Asistencia;
import com.EduTrack.Service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @Autowired
    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping
    public List<Asistencia> obtenerTodas() {
        return asistenciaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> obtenerPorId(@PathVariable Long id) {
        Optional<Asistencia> asistencia = asistenciaService.obtenerPorId(id);
        return asistencia.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Asistencia crear(@RequestBody Asistencia asistencia) {
        return asistenciaService.guardar(asistencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        asistenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
