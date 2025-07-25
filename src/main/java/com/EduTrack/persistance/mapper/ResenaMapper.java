package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.ResenaDTO;
import com.EduTrack.persistance.entity.Resena;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ResenaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    Resena toResena(ResenaDTO dto);

    ResenaDTO toResenaDTO(Resena entity);

    List<ResenaDTO> toResenaDTOList(List<Resena> entities);
}

