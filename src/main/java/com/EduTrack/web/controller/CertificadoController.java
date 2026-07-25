package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.CursoDTO;
import com.EduTrack.domain.service.CertificadoService;
import com.EduTrack.domain.service.UsuariosService;
import com.EduTrack.persistence.entity.Usuarios;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificados")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<CursoDTO> obtenerCursoConProgreso(
            @PathVariable Long cursoId,
            Authentication authentication) {

        String email = authentication.getName();
        Long usuarioId = usuariosService.buscarPorEmail(email)
                .map(Usuarios::getId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        CursoDTO dto = certificadoService.obtenerCursoConProgreso(usuarioId, cursoId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/generar")
    public ResponseEntity<?> generarCertificado(
            @RequestParam Long usuarioId,
            @RequestParam Long cursoId,
            HttpServletResponse response) {
        try {
            byte[] pdfBytes = certificadoService.generarCertificado(usuarioId, cursoId);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=certificado.pdf");
            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();

            return null;

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Curso no completado. No puedes generar el certificado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el certificado.");
        }
    }
}