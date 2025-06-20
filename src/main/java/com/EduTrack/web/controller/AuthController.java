package com.EduTrack.web.controller;

import com.EduTrack.domain.service.EmailService;
import com.EduTrack.domain.service.TokenService;
import com.EduTrack.domain.service.UsuariosService;
import com.EduTrack.persistance.entity.Token;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuariosService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperarPassword(@RequestParam String email) {
        Optional<Usuarios> userOpt = usuarioService.buscarPorEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Correo no registrado"));
        }

        Usuarios usuario = userOpt.get();

        // Generar letra aleatoria A-Z
        char letra = (char) ('A' + new Random().nextInt(26));
        // Generar 4 dígitos aleatorios
        int numeros = 1000 + new Random().nextInt(9000);
        String rawToken = letra + "-" + numeros;

        Token token = new Token();
        token.setToken(rawToken);
        token.setTipo(Token.TokenTipo.RECUPERACION);
        token.setExpiracion(LocalDateTime.now().plusMinutes(30));
        token.setUsuario(usuario);
        token.setUsado(false);
        tokenService.crearToken(token);

        // Enviar solo el token, no link
        emailService.enviarCorreo(email, "Recuperación de contraseña", "Tu token de recuperación es: " + rawToken);

        return ResponseEntity.ok(Map.of("mensaje", "Correo de recuperación enviado", "letraToken", String.valueOf(letra)));
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verificarToken(@RequestParam String token) {
        boolean esValido = tokenService.validarToken(token);
        if (esValido) {
            return ResponseEntity.ok(Map.of("mensaje", "Token válido"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Token inválido o expirado"));
        }
    }

    @PostMapping("/cambiar-password")
    public ResponseEntity<?> cambiarPassword(@RequestParam String token, @RequestParam String nuevaPassword) {
        Optional<Token> tokenOpt = tokenService.obtenerPorToken(token);
        if (tokenOpt.isEmpty() || !tokenService.validarToken(token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Token inválido o expirado"));
        }

        Token t = tokenOpt.get();
        Usuarios usuario = t.getUsuario();
        usuario.setContraseña(passwordEncoder.encode(nuevaPassword));
        usuarioService.guardar(usuario);

        tokenService.marcarComoUsado(t);

        return ResponseEntity.ok(Map.of("mensaje", "Contraseña actualizada correctamente"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication auth = authenticationManager.authenticate(authToken);

            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            Usuarios usuario = usuarioService.buscarPorEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            // 🎯 Aquí usamos el nuevo metodo con el ID del usuario
            String jwt = jwtUtil.generateToken(userDetails, usuario.getId().toString());

            String rol = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_USER");

            return ResponseEntity.ok(Map.of(
                    "mensaje", "Login exitoso",
                    "token", jwt,
                    "rol", rol,
                    "email", email,
                    "nombre", usuario.getNombre()
            ));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales inválidas"));
        }
    }


}
