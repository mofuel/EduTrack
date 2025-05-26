package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.RegistroDTO;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.domain.repository.UsuariosRepository;
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

    public List<Usuarios> listarTodos() {
        return usuariosRepository.getAll();
    }

    public Usuarios guardar(Usuarios usuario) {
        if (usuario.getId() == null || usuario.getId().isEmpty()) {
            usuario.setId(generarIdPersonalizado());
        }
        return usuariosRepository.save(usuario);
    }


    public void registrarUsuario(RegistroDTO dto) {
        Usuarios usuario = new Usuarios();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setDni(dto.getDni());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());

        // Asegurar que el rol tenga el prefijo ROLE_
        String rol = dto.getRol().toLowerCase();
        if (!rol.startsWith("ROLE_")) {
            rol = "ROLE_" + rol;
        }
        usuario.setRol(rol);

        // Encriptar contraseña
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setContraseña(encodedPassword);

        guardar(usuario);
    }


    public Optional<Usuarios> buscarPorId(String id) {
        return usuariosRepository.getById(id);
    }

    public Optional<Usuarios> buscarPorEmail(String email) {
        return usuariosRepository.getByEmail(email);
    }

    public Usuarios actualizar(String id, Usuarios usuarioActualizado) {
        Optional<Usuarios> usuarioExistente = usuariosRepository.getById(id);

        if (usuarioExistente.isPresent()) {
            Usuarios usuario = usuarioExistente.get();

            // Actualizamos campos
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setDni(usuarioActualizado.getDni());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setTelefono(usuarioActualizado.getTelefono());
            usuario.setRol(usuarioActualizado.getRol());
            usuario.setContraseña(usuarioActualizado.getContraseña());

            return usuariosRepository.save(usuario); // Guardamos cambios
        } else {
            return null; // Usuario no encontrado
        }
    }




    public void eliminar(String id) {
        usuariosRepository.delete(id);
    }


    private String generarIdPersonalizado() {
        Optional<String> ultimoIdOpt = usuariosRepository.findLastId(); // o inyecta UsuariosRepositoryImpl

        if (ultimoIdOpt.isPresent()) {
            String ultimoId = ultimoIdOpt.get(); // ej. "U0012"
            int numero = Integer.parseInt(ultimoId.substring(1));
            numero++;
            return "U" + String.format("%04d", numero);
        } else {
            return "U0001";
        }
    }

    public boolean existeEmail(String email) {
        return usuariosRepository.getByEmail(email).isPresent();
    }

    public boolean existeDni(String dni) {
        return usuariosRepository.getByDni(dni).isPresent(); // Si tienes este metodo en repo
    }

    public boolean existeTelefono(String telefono) {
        return usuariosRepository.getByTelefono(telefono).isPresent();
    }





}
