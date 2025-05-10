package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.AvanceDTO;
import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.persistance.entity.Avance;
import com.EduTrack.domain.repository.AvanceRepository;
import com.EduTrack.persistance.mapper.AvanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvanceService {

    @Autowired
    private AvanceRepository avanceRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private AvanceMapper avanceMapper;

    public AvanceDTO registrarAvance(AvanceDTO dto) {
        // Mapear el DTO a la entidad
        Avance avance = avanceMapper.toAvance(dto);

        // Asignar el estudiante/usuario al avance
        avance.setEstudiante(usuariosRepository.getById(dto.getUsuarioId()).orElseThrow());

        // Guardar el avance en la base de datos
        return avanceMapper.toAvanceDTO(avanceRepository.save(avance));
    }

    // Metodo para obtener los avances de un usuario por su ID
    public List<AvanceDTO> obtenerAvancesPorUsuario(String usuarioId) {
        // Obtener la lista de avances del repositorio usando el ID del usuario
        return avanceRepository.findByUsuarioId(usuarioId) // Aquí pasamos un String
                .stream()
                .map(avanceMapper::toAvanceDTO) // Mapear cada entidad a DTO
                .collect(Collectors.toList());
    }
}
