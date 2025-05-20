package com.EduTrack.web.controller;


import com.EduTrack.domain.dto.RegistroDTO;
import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.domain.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    @Autowired
    private UsuariosService usuarioService;

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("registroDTO") @Valid RegistroDTO registroDTO,
                                   BindingResult result, Model model) {

        // Validación de contraseña y confirmación
        if (!registroDTO.getPassword().equals(registroDTO.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Las contraseñas no coinciden");
        }

        if (result.hasErrors()) {
            System.out.println("Errores de validación encontrados:");
            result.getAllErrors().forEach(error -> System.out.println(error.toString()));
            return "registro"; // nombre de la vista HTML
        }

        try {
            usuarioService.registrarUsuario(registroDTO);
            System.out.println("Usuario registrado correctamente");
            return "redirect:/login";
        } catch (Exception e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
            model.addAttribute("error", "Error al registrar el usuario");
            return "registro";
        }
    }
}
