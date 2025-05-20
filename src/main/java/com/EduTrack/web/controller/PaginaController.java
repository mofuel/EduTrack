package com.EduTrack.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.EduTrack.domain.dto.RegistroDTO;

@Controller
public class PaginaController {

    @GetMapping("/dashboard")
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

}
