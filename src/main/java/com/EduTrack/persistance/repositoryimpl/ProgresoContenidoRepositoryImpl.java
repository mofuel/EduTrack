package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.domain.dto.ProgresoContenidoDTO;
import com.EduTrack.domain.dto.ProgresoCursoDTO;
import com.EduTrack.domain.projection.ProgresoCursoProjection;
import com.EduTrack.domain.repository.ContenidoRepository;
import com.EduTrack.domain.repository.ProgresoContenidoRepository;
import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.persistance.crud.ProgresoContenidoCrudRepository;
import com.EduTrack.persistance.entity.Contenido;
import com.EduTrack.persistance.entity.ProgresoContenido;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.persistance.mapper.ProgresoContenidoMapper;
import com.EduTrack.persistance.mapper.ProgresoCursoMapper;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ProgresoContenidoRepositoryImpl implements ProgresoContenidoRepository {

    @Autowired
    private ProgresoContenidoCrudRepository crudRepository;

    @Autowired
    private ProgresoContenidoMapper mapper;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private ProgresoContenidoCrudRepository progresoContenidoCrud;

    @Autowired
    private ProgresoCursoMapper cursoMapper;


    @Override
    public List<ProgresoContenidoDTO> obtenerPorUsuario(String usuarioId) {
        return mapper.toDTOList(crudRepository.findByUsuarioId(usuarioId));
    }

    @Override
    public boolean existeVisualizacion(String usuarioId, Long contenidoId) {
        return crudRepository.existsByUsuarioIdAndContenidoId(usuarioId, contenidoId);
    }

    @Override
    public ProgresoContenidoDTO guardar(ProgresoContenidoDTO dto) {
        // Validación previa
        Optional<ProgresoContenido> existente = progresoContenidoCrud
                .findByUsuarioIdAndContenidoId(dto.getUsuarioId(), dto.getContenidoId());

        if (existente.isPresent()) {
            System.out.println("⚠️ Ya existe progreso para este usuario y contenido. No se guardará de nuevo.");
            return mapper.toDTO(existente.get());
        }

        // Buscar entidades relacionadas
        Usuarios usuario = usuariosRepository.getByEmail(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Contenido contenido = contenidoRepository.getById(dto.getContenidoId())
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));

        // Mapear y asociar
        ProgresoContenido progreso = mapper.toEntity(dto);
        progreso.setUsuario(usuario);
        progreso.setContenido(contenido);

        // Guardar progreso nuevo
        return mapper.toDTO(crudRepository.save(progreso));
    }


    @Override
    public Optional<ProgresoContenidoDTO> obtenerPorId(Long id) {
        return crudRepository.findById(id).map(mapper::toDTO);
    }

    @Override
    public Optional<ProgresoContenidoDTO> obtenerPorUsuarioYContenido(String usuarioId, Long contenidoId) {
        return progresoContenidoCrud.findByUsuarioIdAndContenidoId(usuarioId, contenidoId)
                .map(mapper::toDTO);
    }

    @Override
    public List<ProgresoCursoDTO> obtenerAvancePorUsuario(String usuarioId) {
        var proyecciones = progresoContenidoCrud.obtenerProgresoPorUsuario(usuarioId);
        return proyecciones.stream()
                .map(p -> {
                    ProgresoCursoDTO dto = new ProgresoCursoDTO();
                    dto.setCursoId(p.getCursoId());
                    dto.setNombreCurso(p.getNombreCurso());
                    dto.setPorcentajeAvance(p.getPorcentajeAvance());
                    return dto;
                }).toList();
    }

    @Override
    public Optional<ProgresoCursoDTO> obtenerAvancePorUsuarioYCurso(String usuarioId, Long cursoId) {
        Optional<ProgresoCursoProjection> projection =
                progresoContenidoCrud.obtenerProgresoPorUsuarioYCurso(usuarioId, cursoId);

        return projection.map(p -> {
            ProgresoCursoDTO dto = new ProgresoCursoDTO();
            dto.setCursoId(p.getCursoId());
            dto.setNombreCurso(p.getNombreCurso());
            dto.setPorcentajeAvance(p.getPorcentajeAvance());
            return dto;
        });
    }

}
