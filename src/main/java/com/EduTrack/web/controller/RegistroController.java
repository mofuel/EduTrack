package com.EduTrack.web.controller;


import com.EduTrack.domain.dto.RegistroDTO;
import com.EduTrack.domain.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class RegistroController {

    @Autowired
    private UsuariosService usuarioService;

    @GetMapping("/validar-email")
    public ResponseEntity<Map<String, Boolean>> validarEmail(@RequestParam String email) {
        boolean existe = usuarioService.existeEmail(email);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("existe", existe);
        return ResponseEntity.ok(respuesta);
    }


    @GetMapping("/validar-dni")
    public ResponseEntity<Map<String, Boolean>> validarDni(@RequestParam String dni) {
        boolean existe = usuarioService.existeDni(dni);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("existe", existe);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/validar-telefono")
    public ResponseEntity<Map<String, Boolean>> validarTelefono(@RequestParam String telefono) {
        boolean existe = usuarioService.existeTelefono(telefono);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("existe", existe);
        return ResponseEntity.ok(respuesta);
    }





    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid RegistroDTO registroDTO) {
        if (!registroDTO.getPassword().equals(registroDTO.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Las contraseñas no coinciden");
        }

        try {
            usuarioService.registrarUsuario(registroDTO);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar el usuario: " + e.getMessage());
        }
    }
}
