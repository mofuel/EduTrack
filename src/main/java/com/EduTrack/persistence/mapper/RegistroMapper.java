package com.EduTrack.persistence.mapper;

import com.EduTrack.domain.dto.RegistroDTO;
import com.EduTrack.persistence.entity.Usuarios;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RegistroMapper {

    @Mapping(source = "password", target = "contraseña")
    @Mapping(target = "id", ignore = true) // Se generará en el backend
    @Mapping(target = "avances", ignore = true) // No se setea en el registro
    Usuarios toUsuarioFromRegistroDTO(RegistroDTO dto);
}
