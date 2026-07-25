package com.EduTrack.persistence.mapper;

import com.EduTrack.domain.dto.ProgresoContenidoDTO;
import com.EduTrack.persistence.entity.ProgresoContenido;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgresoContenidoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "contenido.id", target = "contenidoId")
    ProgresoContenidoDTO toDTO(ProgresoContenido entity);

    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(source = "contenidoId", target = "contenido.id")
    ProgresoContenido toEntity(ProgresoContenidoDTO dto);

    List<ProgresoContenidoDTO> toDTOList(List<ProgresoContenido> entities);
}
