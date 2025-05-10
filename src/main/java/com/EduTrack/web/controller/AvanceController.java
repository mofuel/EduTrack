package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.AvanceDTO;
import com.EduTrack.persistance.entity.Avance;
import com.EduTrack.domain.service.AvanceService;
import com.EduTrack.persistance.mapper.AvanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avance")
public class AvanceController {

    @Autowired
    private AvanceService avanceService;

    @Autowired
    private AvanceMapper avanceMapper;

    @GetMapping("/usuario/{id}")
    public List<AvanceDTO> obtenerAvancesPorUsuario(@PathVariable String id) {
        return avanceService.obtenerAvancesPorUsuario(id);  // El servicio devuelve una lista de AvanceDTO
    }

    @PostMapping
    public AvanceDTO registrarAvance(@RequestBody Avance avance) {
        // Convertir la entidad Avance a AvanceDTO antes de pasarla al servicio
        AvanceDTO avanceDTO = avanceMapper.toAvanceDTO(avance);

        // Pasar el DTO al servicio
        return avanceService.registrarAvance(avanceDTO);
    }

}
