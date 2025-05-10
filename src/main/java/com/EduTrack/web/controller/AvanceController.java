package com.EduTrack.web.controller;

import com.EduTrack.persistance.entity.Avance;
import com.EduTrack.domain.service.AvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avance")
public class AvanceController {

    @Autowired
    private AvanceService avanceService;

    @GetMapping("/usuario/{id}")
    public List<Avance> obtenerAvancesPorUsuario(@PathVariable Long id) {
        return avanceService.obtenerAvancesPorUsuario(id);
    }

    @PostMapping
    public Avance registrarAvance(@RequestBody Avance avance) {
        return avanceService.registrarAvance(avance);
    }
}
