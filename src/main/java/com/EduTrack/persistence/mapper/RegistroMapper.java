package com.EduTrack.persistence.mapper;

import com.EduTrack.domain.dto.RegistroDTO;
import com.EduTrack.persistence.entity.Usuarios;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RegistroMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", constant = "true")
    Usuarios toUsuarioFromRegistroDTO(RegistroDTO dto);
}
