package com.EduTrack.domain.repository;

import com.EduTrack.domain.dto.ProgresoContenidoDTO;
import com.EduTrack.domain.dto.ProgresoCursoDTO;

import java.util.List;
import java.util.Optional;

public interface ProgresoContenidoRepository {

    List<ProgresoContenidoDTO> obtenerPorUsuario(String usuarioId);

    boolean existeVisualizacion(String usuarioId, Long contenidoId);

    ProgresoContenidoDTO guardar(ProgresoContenidoDTO progresoDTO);

    Optional<ProgresoContenidoDTO> obtenerPorId(Long id);

    Optional<ProgresoContenidoDTO> obtenerPorUsuarioYContenido(String usuarioId, Long contenidoId);

    List<ProgresoCursoDTO> obtenerAvancePorUsuario(String usuarioId);

    Optional<ProgresoCursoDTO> obtenerAvancePorUsuarioYCurso(String usuarioId, Long cursoId);

}
