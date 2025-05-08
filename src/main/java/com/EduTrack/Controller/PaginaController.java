package com.EduTrack.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/usuarios")
    public String mostrarPaginaUsuarios() {
        return "testUsuario";
    }
}
