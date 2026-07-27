package com.EduTrack.web.controller;

import com.EduTrack.persistence.entity.Usuarios;
import com.EduTrack.domain.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import java.util.List;

@RestController
@RequestMapping("/dash/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;



    // Obtener un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuariosService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Actualizar un usuario existente
    @PutMapping("/actualizar/{id}")
    public Usuarios actualizarUsuario(@PathVariable Long id, @RequestBody Usuarios usuarioActualizado) {
        return usuariosService.actualizar(id, usuarioActualizado);
    }

    // Eliminar un usuario
    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuariosService.eliminar(id);
    }

    @GetMapping
    public Page<Usuarios> obtenerUsuarios(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return usuariosService.listarTodos(pageable);
    }
}
