package com.EduTrack.Controller;

import com.EduTrack.DTO.NotaDTO;
import com.EduTrack.Model.Notas;
import com.EduTrack.Service.NotasService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notas")
public class NotasController {
    @Autowired
    private NotasService notasService;

    @GetMapping()
    public List<Notas> obtenerTodas(){
        return notasService.obtenerNotas();
    }

    @PostMapping
    public Notas crearNota(@RequestBody NotaDTO notaDTO){
        return notasService.guardarNotaDesdeDTO(notaDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Notas> obtenerPorId(@PathVariable Long id) {
        Optional<Notas> nota = notasService.buscarPorId(id);
        return nota.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notas> actualizarNota(@PathVariable Long id, @RequestBody Notas nota) {
        try {
            Notas notaActualizada = notasService.actualizarNota(id, nota);
            return ResponseEntity.ok(notaActualizada);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNota(@PathVariable Long id) {
        notasService.eliminarNota(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estudiante/{id}")
    public List<Notas> obtenerNotasPorEstudiante(@PathVariable("id") String estudianteId) {
        return notasService.obtenerNotasPorEstudianteId(estudianteId);
    }

    @GetMapping("/promedio/{id}")
    public Double obtenerPromedioPorEstudiante(@PathVariable("id") String estudianteId) {
        return notasService.obtenerPromedio(estudianteId);
    }

    @GetMapping("/estudiante/{id}/fechas")
    public List<Notas> obtenerNotasPorFechas(
            @PathVariable("id") String estudianteId,
            @RequestParam("inicio") @DateTimeFormat(pattern = "dd-MM-yyyy") Date inicio,
            @RequestParam("fin") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fin) {
        return notasService.obtenerNotasPorFecha(estudianteId, inicio, fin);
    }

}


