package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.ProgresoContenidoDTO;
import com.EduTrack.domain.dto.ProgresoCursoDTO;
import com.EduTrack.domain.repository.ProgresoContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgresoContenidoService {

    @Autowired
    private ProgresoContenidoRepository progresoContenidoRepository;

    public ProgresoContenidoDTO guardar(ProgresoContenidoDTO dto) {
        try {
            Optional<ProgresoContenidoDTO> existente = progresoContenidoRepository
                    .obtenerPorUsuarioYContenido(dto.getUsuarioId(), dto.getContenidoId());

            if (existente.isPresent()) return existente.get();

            return progresoContenidoRepository.guardar(dto);

        } catch (DataIntegrityViolationException e) {
            System.out.println("Ya existe progreso (detectado por índice único). Recuperando...");

            for (int i = 0; i < 3; i++) {
                Optional<ProgresoContenidoDTO> existente = progresoContenidoRepository
                        .obtenerPorUsuarioYContenido(dto.getUsuarioId(), dto.getContenidoId());

                if (existente.isPresent()) return existente.get();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
            }

            throw new RuntimeException("Error al obtener progreso existente después del duplicado");
        }
    }

    public List<ProgresoContenidoDTO> obtenerProgresosPorUsuario(Long usuarioId) {
        return progresoContenidoRepository.obtenerPorUsuario(usuarioId);
    }

    public boolean existeProgreso(Long usuarioId, Long contenidoId) {
        return progresoContenidoRepository.existeVisualizacion(usuarioId, contenidoId);
    }

    public Optional<ProgresoContenidoDTO> obtenerPorId(Long id) {
        return progresoContenidoRepository.obtenerPorId(id);
    }

    public List<ProgresoCursoDTO> obtenerAvanceCursosPorUsuario(Long usuarioId) {
        return progresoContenidoRepository.obtenerAvancePorUsuario(usuarioId);
    }

    public Optional<ProgresoCursoDTO> obtenerAvancePorUsuarioYCurso(Long usuarioId, Long cursoId) {
        return progresoContenidoRepository.obtenerAvancePorUsuarioYCurso(usuarioId, cursoId);
    }
}