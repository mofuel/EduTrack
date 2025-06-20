package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.ModuloConContenidoDTO;
import com.EduTrack.domain.dto.ModuloDTO;
import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.domain.service.ModuloService;
import com.EduTrack.persistance.entity.Modulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modulos")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping("/curso/{cursoId}")
    public List<ModuloConContenidoDTO> listarModulosConContenidos(@PathVariable Long cursoId) {
        return moduloService.listarConContenidosPorCurso(cursoId);
    }

    @GetMapping("/{id}")
    public Optional<Modulo> obtenerPorId(@PathVariable Long id) {
        return moduloService.obtenerPorId(id);
    }

    @PostMapping
    public Modulo crear(@RequestBody ModuloDTO moduloDTO) {
        return moduloService.guardarDesdeDTO(moduloDTO);
    }

    @PutMapping("/{id}")
    public Modulo actualizar(@PathVariable Long id, @RequestBody ModuloDTO dto) {
        return moduloService.actualizarDesdeDTO(id, dto);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        moduloService.eliminarModulo(id);
    }
}
