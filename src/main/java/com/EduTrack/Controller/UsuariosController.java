package com.EduTrack.Controller;

import com.EduTrack.Model.Usuarios;
import com.EduTrack.Service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
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

    // Guardar un nuevo usuario
    @PostMapping("/guardar")
    public Usuarios guardarUsuario(@RequestBody Usuarios usuario) {
        return usuariosService.guardar(usuario);
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
