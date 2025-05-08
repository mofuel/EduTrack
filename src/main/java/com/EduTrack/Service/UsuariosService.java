package com.EduTrack.Service;

import com.EduTrack.Model.Usuarios;
import com.EduTrack.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public List<Usuarios> listarTodos() {
        return usuariosRepository.findAll();
    }

    public Usuarios guardar(Usuarios usuario) {
        if (usuario.getId() == null || usuario.getId().isEmpty()) {
            usuario.setId(generarIdPersonalizado());
        }
        return usuariosRepository.save(usuario);
    }


    public Optional<Usuarios> buscarPorId(String id) {
        return usuariosRepository.findById(id);
    }

    public Usuarios actualizar(String id, Usuarios usuarioActualizado) {
        Optional<Usuarios> usuarioExistente = usuariosRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuarios usuario = usuarioExistente.get();
            // Aquí puedes actualizar los campos del usuario si es necesario
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellido(usuarioActualizado.getApellido());
            usuario.setDni(usuarioActualizado.getDni());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setTelefono(usuarioActualizado.getTelefono());
            usuario.setRol(usuarioActualizado.getRol());
            usuario.setContraseña(usuarioActualizado.getContraseña());

            return usuariosRepository.save(usuario);  // Actualiza el usuario
        } else {
            return null;  // Usuario no encontrado
        }
    }



    public void eliminar(String id) {
        usuariosRepository.deleteById(id);
    }


    private String generarIdPersonalizado() {
        long cantidadUsuarios = usuariosRepository.count() + 1;
        return "U" + String.format("%04d", cantidadUsuarios); // Ej: U0001, U0002...
    }



}
