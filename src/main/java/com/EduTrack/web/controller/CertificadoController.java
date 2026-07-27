package com.EduTrack.web.controller;

import com.EduTrack.domain.dto.CursoDTO;
import com.EduTrack.domain.service.CertificadoService;
import com.EduTrack.domain.service.UsuariosService;
import com.EduTrack.persistence.entity.Usuarios;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public ResponseEntity<byte[]> generarCertificado(
            @RequestParam Long usuarioId,
            @RequestParam Long cursoId) {
        byte[] pdfBytes = certificadoService.generarCertificado(usuarioId, cursoId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("certificado.pdf").build());

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}