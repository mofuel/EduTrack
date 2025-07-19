package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.ProgresoCursoDTO;
import com.EduTrack.domain.projection.ProgresoCursoProjection;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgresoCursoMapper {

    ProgresoCursoDTO toDTO(ProgresoCursoProjection projection);

    List<ProgresoCursoDTO> toDTOList(List<ProgresoCursoProjection> projections);
}
