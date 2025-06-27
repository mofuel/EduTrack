package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.PagoDTO;
import com.EduTrack.persistance.entity.Pago;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "curso.id", target = "cursoId")
    PagoDTO toDTO(Pago pago);

    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(source = "cursoId", target = "curso.id")
    Pago toEntity(PagoDTO dto);

    List<PagoDTO> toDTOList(List<Pago> pagos);
}
