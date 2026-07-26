package com.EduTrack.web.controller;

import com.EduTrack.domain.service.EmailService;
import com.EduTrack.domain.service.TokenService;
import com.EduTrack.domain.service.UsuariosService;
import com.EduTrack.persistence.entity.Token;
import com.EduTrack.persistence.entity.Usuarios;
import com.EduTrack.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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
        Usuarios usuario = usuarioService.buscarPorEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Correo no registrado"));

        String rawToken = UUID.randomUUID().toString();

        Token token = new Token();
        token.setToken(rawToken);
        token.setTipo(Token.TokenTipo.RECUPERACION);
        token.setExpiracion(LocalDateTime.now().plusMinutes(30));
        token.setUsuario(usuario);
        token.setUsado(false);
        tokenService.crearToken(token);

        emailService.enviarCorreo(email, "Recuperación de contraseña", "Tu token de recuperación es: " + rawToken);

        return ResponseEntity.ok(Map.of("mensaje", "Correo de recuperación enviado"));
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verificarToken(@RequestParam String token) {
        boolean esValido = tokenService.validarToken(token);
        if (esValido) {
            return ResponseEntity.ok(Map.of("mensaje", "Token válido"));
        }
        throw new IllegalArgumentException("Token inválido o expirado");
    }

    @PostMapping("/cambiar-password")
    public ResponseEntity<?> cambiarPassword(@RequestParam String token, @RequestParam String nuevaPassword) {
        Token t = tokenService.obtenerPorToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido o expirado"));

        if (!tokenService.validarToken(token)) {
            throw new IllegalArgumentException("Token inválido o expirado");
        }

        Usuarios usuario = t.getUsuario();
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
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
            throw new AuthenticationException("Credenciales inválidas") {};
        }
    }
}
