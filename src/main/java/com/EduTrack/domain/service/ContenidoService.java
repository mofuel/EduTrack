package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.ContenidoDTO;
import com.EduTrack.domain.repository.ContenidoRepository;
import com.EduTrack.persistance.entity.Contenido;
import com.EduTrack.persistance.entity.Modulo;
import com.EduTrack.persistance.mapper.ContenidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContenidoService {

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private ContenidoMapper contenidoMapper;

    public List<ContenidoDTO> listarPorModuloDTO(Long moduloId) {
        return contenidoRepository.getByModuloId(moduloId).stream()
                .map(contenidoMapper::toDTO)
                .toList();
    }

    public Optional<ContenidoDTO> obtenerPorId(Long id) {
        return contenidoRepository.getById(id)
                .map(contenidoMapper::toDTO);
    }

    public ContenidoDTO guardarDesdeDTO(ContenidoDTO dto) {
        Modulo modulo = moduloService.obtenerModuloPorId(dto.getModuloId())
                .orElseThrow(() -> new IllegalArgumentException("El módulo no existe"));

        Contenido contenido = contenidoMapper.toEntity(dto);
        contenido.setModulo(modulo); // Asignación manual como en el mapper

        Contenido guardado = contenidoRepository.save(contenido);
        return contenidoMapper.toDTO(guardado);
    }

    public boolean eliminarContenido(Long id) {
        Optional<Contenido> contenido = contenidoRepository.getById(id);
        if (contenido.isPresent()) {
            contenidoRepository.delete(id);
            return true;
        }
        return false;
    }

    public Optional<ContenidoDTO> obtenerDTOPorId(Long id) {
        return contenidoRepository.getById(id).map(contenidoMapper::toDTO);
    }


}
