package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.ContenidoDTO;
import com.EduTrack.persistance.entity.Contenido;
import com.EduTrack.persistance.entity.Modulo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContenidoMapper {

    @Mapping(source = "modulo.id", target = "moduloId")
    ContenidoDTO toDTO(Contenido contenido);

    @Mapping(target = "modulo", ignore = true) // Se asignará manualmente después
    Contenido toEntity(ContenidoDTO dto);

    List<ContenidoDTO> toDTOList(List<Contenido> contenidos);

    @AfterMapping
    default void asignarModulo(@MappingTarget Contenido contenido, @Context Modulo modulo) {
        contenido.setModulo(modulo);
    }
}
