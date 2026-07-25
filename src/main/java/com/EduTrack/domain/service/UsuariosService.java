package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.RegistroDTO;
import com.EduTrack.persistence.entity.Usuarios;
import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.persistence.mapper.RegistroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistroMapper registroMapper;

    public List<Usuarios> listarTodos() {
        return usuariosRepository.getAll();
    }

    public Usuarios guardar(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    public void registrarUsuario(RegistroDTO dto) {
        Usuarios usuario = registroMapper.toUsuarioFromRegistroDTO(dto);
        usuario.setRol(dto.getRol());

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(encodedPassword);

        guardar(usuario);
    }

    public Optional<Usuarios> buscarPorId(Long id) {
        return usuariosRepository.getById(id);
    }

    public Optional<Usuarios> buscarPorEmail(String email) {
        return usuariosRepository.getByEmail(email);
    }

    public Usuarios actualizar(Long id, Usuarios usuarioActualizado) {
        Optional<Usuarios> usuarioExistente = usuariosRepository.getById(id);

        if (usuarioExistente.isPresent()) {
            Usuarios usuario = usuarioExistente.get();

            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setDni(usuarioActualizado.getDni());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setTelefono(usuarioActualizado.getTelefono());
            usuario.setRol(usuarioActualizado.getRol());

            if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
            }

            return usuariosRepository.save(usuario);
        } else {
            return null;
        }
    }

    public void eliminar(Long id) {
        usuariosRepository.delete(id);
    }

    public boolean existeEmail(String email) {
        return usuariosRepository.getByEmail(email).isPresent();
    }

    public boolean existeDni(String dni) {
        return usuariosRepository.getByDni(dni).isPresent();
    }

    public boolean existeTelefono(String telefono) {
        return usuariosRepository.getByTelefono(telefono).isPresent();
    }
}
