package com.EduTrack.persistence.mapper;

import com.EduTrack.domain.dto.UsuariosDTO;
import com.EduTrack.persistence.entity.Usuarios;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {

    // Mapeo de entidad a DTO
    UsuariosDTO toUsuariosDTO(Usuarios usuario);

    // Mapeo inverso de DTO a entidad
    @InheritInverseConfiguration
    Usuarios toUsuarios(UsuariosDTO usuarioDTO);
}
