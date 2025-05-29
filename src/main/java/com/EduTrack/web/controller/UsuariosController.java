package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.RegistroDTO;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.domain.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dash/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    // Obtener lista de usuarios
    @GetMapping
    public List<Usuarios> obtenerUsuarios() {
        return usuariosService.listarTodos();
    }

    // Obtener un usuario por su ID
    @GetMapping("/{id}")
    public Usuarios obtenerUsuarioPorId(@PathVariable String id) {
        return usuariosService.buscarPorId(id).orElse(null);
    }


    // Actualizar un usuario existente
    @PutMapping("/actualizar/{id}")
    public Usuarios actualizarUsuario(@PathVariable String id, @RequestBody Usuarios usuarioActualizado) {
        return usuariosService.actualizar(id, usuarioActualizado);
    }

    // Eliminar un usuario
    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable String id) {
        usuariosService.eliminar(id);
    }
}
