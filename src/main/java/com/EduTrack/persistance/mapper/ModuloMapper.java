package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.ModuloConContenidoDTO;
import com.EduTrack.domain.dto.ModuloDTO;
import com.EduTrack.persistance.entity.Modulo;
import com.EduTrack.persistance.entity.Curso;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ContenidoMapper.class})
public interface ModuloMapper {
    @Mapping(source = "curso.id", target = "cursoId")
    ModuloDTO toDTO(Modulo modulo);

    List<ModuloDTO> toDTOList(List<Modulo> modulos);

    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "contenidos", target = "contenidos")
    ModuloConContenidoDTO toConContenidoDTO(Modulo modulo);

    List<ModuloConContenidoDTO> toConContenidoDTOList(List<Modulo> modulos);

    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "contenidos", ignore = true)
    Modulo toEntity(ModuloDTO dto);

    @AfterMapping
    default void asignarCurso(@MappingTarget Modulo modulo, @Context Curso curso) {
        modulo.setCurso(curso);
    }
}
