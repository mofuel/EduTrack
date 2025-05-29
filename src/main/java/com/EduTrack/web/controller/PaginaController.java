package com.EduTrack.web.controller;

import com.EduTrack.domain.service.TokenService;
import com.EduTrack.domain.service.UsuariosService;
import com.EduTrack.persistance.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.EduTrack.domain.dto.RegistroDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PaginaController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/api/dashboard")
    public String mostrarDashboard(){
        return "dashboard";
    }

    @GetMapping("/usuarios")
    public String mostrarPaginaUsuarios() {
        return "usuario";
    }

    @GetMapping("/cursos")
    public String mostrarPaginaCursos(){return "curso";}

    @GetMapping("/login")
    public String mostrarLogin(){return "login";}

    @GetMapping("/registro")
    public String mostraRegistro(Model model){
        model.addAttribute("RegistroDTO", new RegistroDTO());
        return "registro";
    }

    @GetMapping("/index")
    public String mostrarIndex(){return "index";}

    @GetMapping("/indexdocente")
    public String mostrarIndexDocente(){return "indexdocente";}

    @GetMapping("/cambiarcontraseña")
    public String mostrarFormularioCambio(@RequestParam("token") String token, Model model) {
        Optional<Token> tokenOpt = tokenService.obtenerPorToken(token);

        if (tokenOpt.isEmpty() || tokenService.validarToken(token) == false) {
            model.addAttribute("errorMensaje", "El enlace para cambiar la contraseña ha expirado o es inválido.");
            return "errorToken";  // Vista HTML que muestra el mensaje de error
        }

        model.addAttribute("token", token);
        return "cambiarContraseña";  // Vista HTML con el formulario para cambiar contraseña
    }

}
