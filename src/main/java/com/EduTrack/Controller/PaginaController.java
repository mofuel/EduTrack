package com.EduTrack.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/dashboard")
    public String mostrarDashboard(){
        return "testDashboard";
    }

    @GetMapping("/usuarios")
    public String mostrarPaginaUsuarios() {
        return "testUsuario";
    }

    @GetMapping("/cursos")
    public String mostrarPaginaCursos(){
        return "testCurso";
    }

}
