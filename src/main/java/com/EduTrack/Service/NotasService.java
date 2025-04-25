package com.EduTrack.Service;

import com.EduTrack.DTO.NotaDTO;
import com.EduTrack.Model.Notas;
import com.EduTrack.Model.Usuarios;
import com.EduTrack.Repository.UsuariosRepository;
import com.EduTrack.Repository.NotasRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotasService {

    private final NotasRepository notasRepository;
    private final UsuariosRepository usuariosRepository;

    @Autowired
    public NotasService(NotasRepository notasRepository, UsuariosRepository usuariosRepository){
        this.notasRepository = notasRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public ArrayList<Notas> obtenerNotas(){
        return (ArrayList<Notas>) notasRepository.findAll();
    }

    public Notas guardarNotaDesdeDTO(NotaDTO notaDTO) {
        Optional<Usuarios> usuarioOpt = usuariosRepository.findById(notaDTO.getUsuarioId());
        if (usuarioOpt.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + notaDTO.getUsuarioId());
        }

        Notas nota = new Notas();
        nota.setEstudiante(usuarioOpt.get());
        nota.setCalificacion(notaDTO.getCalificacion());
        nota.setFecha(notaDTO.getFecha());
        nota.setComentario(notaDTO.getComentario());

        return notasRepository.save(nota);
    }

    public Optional<Notas> buscarPorId(Long id) {
        return notasRepository.findById(id);
    }

    public void eliminarNota(Long id) {
        notasRepository.deleteById(id);
    }

    public Notas actualizarNota(Long id, Notas nuevaNota) {
        Optional<Notas> notaExistenteOpt = notasRepository.findById(id);
        if (notaExistenteOpt.isEmpty()) {
            throw new EntityNotFoundException("Nota no encontrada con ID: " + id);
        }

        Notas notaExistente = notaExistenteOpt.get();
        notaExistente.setCalificacion(nuevaNota.getCalificacion());
        notaExistente.setFecha(nuevaNota.getFecha());
        notaExistente.setComentario(nuevaNota.getComentario());

        if (nuevaNota.getEstudiante() != null && nuevaNota.getEstudiante().getId() != null) {
            Optional<Usuarios> usuarioOpt = usuariosRepository.findById(nuevaNota.getEstudiante().getId());
            if (usuarioOpt.isEmpty()) {
                throw new EntityNotFoundException("Usuario no encontrado con ID: " + nuevaNota.getEstudiante().getId());
            }
            notaExistente.setEstudiante(usuarioOpt.get());
        }

        return notasRepository.save(notaExistente);
    }


    public List<Notas> obtenerNotasPorEstudianteId(String estudianteId) {
        return notasRepository.findByEstudianteId(estudianteId);
    }

    public Double obtenerPromedio(String estudianteId) {
        List<Notas> notas = notasRepository.findByEstudianteId(estudianteId);
        if (notas.isEmpty()) {
            return 0.0;
        }

        double suma = 0;
        for (Notas nota : notas) {
            suma += nota.getCalificacion();
        }

        return suma / notas.size();
    }

    public List<Notas> obtenerNotasPorFecha(String estudianteId, Date inicio, Date fin) {
        return notasRepository.findByEstudianteIdAndFechaBetween(estudianteId, inicio, fin);
    }





}
