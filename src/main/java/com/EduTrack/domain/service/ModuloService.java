package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.ContenidoDTO;
import com.EduTrack.domain.dto.ModuloConContenidoDTO;
import com.EduTrack.domain.dto.ModuloDTO;
import com.EduTrack.domain.repository.ContenidoRepository;
import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.domain.repository.ModuloRepository;
import com.EduTrack.persistance.entity.Contenido;
import com.EduTrack.persistance.entity.Curso;
import com.EduTrack.persistance.entity.Modulo;
import com.EduTrack.persistance.mapper.ContenidoMapper;
import com.EduTrack.persistance.mapper.ModuloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ModuloMapper moduloMapper;

    public List<Modulo> listarPorCurso(Long cursoId) {
        return moduloRepository.getByCursoId(cursoId);
    }

    public Optional<Modulo> obtenerPorId(Long id) {
        return moduloRepository.getById(id);
    }

    public Modulo guardarModulo(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    public boolean eliminarModulo(Long id) {
        Optional<Modulo> modulo = moduloRepository.getById(id);
        if (modulo.isPresent()) {
            moduloRepository.delete(id);
            return true;
        }
        return false;
    }

    public Modulo guardarDesdeDTO(ModuloDTO dto) {
        // Validación básica
        if (dto.getCursoId() == null) {
            throw new IllegalArgumentException("El ID del curso es obligatorio.");
        }

        Curso curso = cursoRepository.getById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + dto.getCursoId()));

        Modulo modulo = new Modulo();
        modulo.setTitulo(dto.getTitulo());
        modulo.setDescripcion(dto.getDescripcion());
        modulo.setCurso(curso);

        return moduloRepository.save(modulo);
    }

    public Optional<Modulo> obtenerModuloPorId(Long id) {
        return moduloRepository.getById(id);
    }

    public List<ModuloConContenidoDTO> listarConContenidosPorCurso(Long cursoId) {
        List<Modulo> modulos = moduloRepository.getByCursoId(cursoId);
        return moduloMapper.toConContenidoDTOList(modulos);
    }

    public Modulo actualizarDesdeDTO(Long id, ModuloDTO dto) {
        Modulo modulo = moduloRepository.getById(id)
                .orElseThrow(() -> new RuntimeException("Módulo no encontrado con ID: " + id));

        if (dto.getCursoId() == null) {
            throw new IllegalArgumentException("El ID del curso es obligatorio.");
        }

        Curso curso = cursoRepository.getById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + dto.getCursoId()));

        modulo.setTitulo(dto.getTitulo());
        modulo.setDescripcion(dto.getDescripcion());
        modulo.setCurso(curso);

        return moduloRepository.save(modulo);
    }

}
