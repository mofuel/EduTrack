package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.CarritoDTO;
import com.EduTrack.persistance.entity.Carrito;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarritoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "curso.nombre", target = "nombreCurso")
    @Mapping(source = "curso.precio", target = "precio")
    @Mapping(source = "curso.imagen", target = "imagen")
    @Mapping(source = "curso.docente.nombre", target = "docenteNombre")
    CarritoDTO toDTO(Carrito carrito);

    List<CarritoDTO> toDTOList(List<Carrito> carritoList);
}
