package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.UsuariosDTO;
import com.EduTrack.persistance.entity.Usuarios;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    // Mapeo de entidad a DTO
    UsuariosDTO toUsuariosDTO(Usuarios usuario);

    // Mapeo inverso de DTO a entidad
    @InheritInverseConfiguration
    @Mapping(target = "avances", ignore = true) // se maneja por separado
    Usuarios toUsuarios(UsuariosDTO usuarioDTO);
}
