package com.EduTrack.Repository;

import com.EduTrack.domain.dto.AvanceDTO;
import com.EduTrack.persistance.entity.Avance;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.persistance.mapper.AvanceMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvanceMapperTest {


    private final AvanceMapper avanceMapper = Mappers.getMapper(AvanceMapper.class);

    @Test
    public void testToAvanceDTO() {
        // Datos de entrada
        Avance avance = new Avance();
        avance.setId(1L);
        avance.setDescripcion("Progreso de estudiante");
        avance.setProgreso(75.0);
        avance.setEstudiante(new Usuarios()); // Asegúrate de completar esto si es necesario

        // Mapeamos a DTO
        AvanceDTO avanceDTO = avanceMapper.toAvanceDTO(avance);

        // Comprobamos que el mapeo fue exitoso
        assertEquals(avance.getId(), avanceDTO.getId());
        assertEquals(avance.getDescripcion(), avanceDTO.getDescripcion());
        assertEquals(avance.getProgreso(), avanceDTO.getProgreso());
    }

    @Test
    public void testToAvance() {
        // Datos de entrada
        AvanceDTO avanceDTO = new AvanceDTO();
        avanceDTO.setId(1L);
        avanceDTO.setDescripcion("Progreso de estudiante");
        avanceDTO.setProgreso(75.0);
        avanceDTO.setUsuarioId("123");

        // Mapeamos a entidad
        Avance avance = avanceMapper.toAvance(avanceDTO);

        // Comprobamos que el mapeo fue exitoso
        assertEquals(avanceDTO.getId(), avance.getId());
        assertEquals(avanceDTO.getDescripcion(), avance.getDescripcion());
        assertEquals(avanceDTO.getProgreso(), avance.getProgreso());
    }
}
