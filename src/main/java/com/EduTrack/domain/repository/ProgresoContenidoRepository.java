package com.EduTrack.domain.repository;

import com.EduTrack.domain.dto.ProgresoContenidoDTO;
import com.EduTrack.domain.dto.ProgresoCursoDTO;

import java.util.List;
import java.util.Optional;

public interface ProgresoContenidoRepository {

    List<ProgresoContenidoDTO> obtenerPorUsuario(Long  usuarioId);

    boolean existeVisualizacion(Long  usuarioId, Long contenidoId);

    ProgresoContenidoDTO guardar(ProgresoContenidoDTO progresoDTO);

    Optional<ProgresoContenidoDTO> obtenerPorId(Long id);

    Optional<ProgresoContenidoDTO> obtenerPorUsuarioYContenido(Long  usuarioId, Long contenidoId);

    List<ProgresoCursoDTO> obtenerAvancePorUsuario(Long  usuarioId);

    Optional<ProgresoCursoDTO> obtenerAvancePorUsuarioYCurso(Long  usuarioId, Long cursoId);

}
