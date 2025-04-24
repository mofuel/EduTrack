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

    public void eliminar(String id) {
        usuariosRepository.deleteById(id);
    }


    private String generarIdPersonalizado() {
        long cantidadUsuarios = usuariosRepository.count() + 1;
        return "U" + String.format("%04d", cantidadUsuarios); // Ej: U0001, U0002...
    }



}
