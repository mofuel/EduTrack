package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.AvanceDTO;
import com.EduTrack.persistance.entity.Avance;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AvanceMapper {

    @Mapping(source = "estudiante.id", target = "usuarioId")
    AvanceDTO toAvanceDTO(Avance avance);

    @InheritInverseConfiguration
    @Mapping(target = "estudiante", ignore = true) // la asignarás manualmente
    Avance toAvance(AvanceDTO avanceDTO);
}
