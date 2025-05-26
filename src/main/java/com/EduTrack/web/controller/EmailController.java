package com.EduTrack.web.controller;

import com.EduTrack.domain.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/correo")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar")
    public String enviarCorreo(
            @RequestParam String para,
            @RequestParam String asunto,
            @RequestParam String mensaje) {
        emailService.enviarCorreo(para, asunto, mensaje);
        return "Correo enviado exitosamente.";
    }
}
