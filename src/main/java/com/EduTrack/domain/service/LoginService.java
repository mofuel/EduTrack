package com.EduTrack.domain.service;

import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.persistance.entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UsuariosRepository usuariosRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuario = usuariosRepository.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        // Agrega el rol como autoridad
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol()));

        return new User(
                usuario.getEmail(),
                usuario.getContraseña(),
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol()))
        );
    }
}
