package com.EduTrack.persistance.mapper;

import com.EduTrack.domain.dto.CursoDTO;
import com.EduTrack.persistance.entity.Curso;
import com.EduTrack.persistance.entity.Usuarios;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(source = "docente.id", target = "docenteId")
    @Mapping(source = "docente.nombre", target = "docenteNombre")
    @Mapping(source = "activo", target = "activo")
    @Mapping(source = "requisitos", target = "requisitos")
    @Mapping(source = "objetivos", target = "objetivos")
    @Mapping(source = "incluye", target = "incluye")
    @Mapping(source = "imagen", target = "imagen")
    @Mapping(source = "disponibleParaCompra", target = "disponibleParaCompra") // ✅ NUEVO
    @Mapping(target = "estudiantesIds", expression = "java(curso.getEstudiantes() != null ? curso.getEstudiantes().stream().map(Usuarios::getId).toList() : null)")
    CursoDTO toDTO(Curso curso);

    @Mapping(target = "activo", constant = "true")
    @Mapping(source = "requisitos", target = "requisitos")
    @Mapping(source = "objetivos", target = "objetivos")
    @Mapping(source = "incluye", target = "incluye")
    @Mapping(source = "imagen", target = "imagen")
    @Mapping(source = "disponibleParaCompra", target = "disponibleParaCompra") // ✅ NUEVO
    Curso toEntity(CursoDTO dto, @Context Usuarios docente, @Context List<Usuarios> estudiantes);

    @AfterMapping
    default void asignarDocenteYEstudiantes(@MappingTarget Curso curso, @Context Usuarios docente, @Context List<Usuarios> estudiantes) {
        curso.setDocente(docente);
        curso.setEstudiantes(estudiantes);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "docente", ignore = true)
    @Mapping(target = "estudiantes", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(source = "requisitos", target = "requisitos")
    @Mapping(source = "objetivos", target = "objetivos")
    @Mapping(source = "incluye", target = "incluye")
    @Mapping(source = "imagen", target = "imagen")
    @Mapping(source = "disponibleParaCompra", target = "disponibleParaCompra") // ✅ NUEVO
    void updateEntityFromDTO(CursoDTO dto, @MappingTarget Curso curso);
}
