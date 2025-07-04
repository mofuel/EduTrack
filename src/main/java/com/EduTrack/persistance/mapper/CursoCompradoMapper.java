package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.CursoCompradoDTO;
import com.EduTrack.persistance.entity.CursoComprado;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoCompradoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "curso.nombre", target = "nombreCurso")
    @Mapping(source = "curso.precio", target = "precio")
    @Mapping(source = "curso.imagen", target = "imagen")
    @Mapping(source = "curso.docente.nombre", target = "docenteNombre")
    CursoCompradoDTO toDTO(CursoComprado cursoComprado);

    List<CursoCompradoDTO> toDTOList(List<CursoComprado> lista);
}
