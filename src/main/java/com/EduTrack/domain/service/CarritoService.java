package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.CarritoDTO;
import com.EduTrack.domain.repository.CarritoRepository;
import com.EduTrack.persistence.entity.Carrito;
import com.EduTrack.persistence.entity.Curso;
import com.EduTrack.persistence.entity.Usuarios;
import com.EduTrack.persistence.mapper.CarritoMapper;
import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.domain.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private CarritoMapper carritoMapper;

    public List<CarritoDTO> listarCarritoPorUsuario(Long usuarioId) {
        List<Carrito> carrito = carritoRepository.getByUsuarioId(usuarioId);
        return carritoMapper.toDTOList(carrito);
    }

    public Optional<CarritoDTO> agregarCursoAlCarrito(Long usuarioId, Long cursoId) {
        Optional<Usuarios> usuarioOpt = usuarioRepository.getById(usuarioId);
        Optional<Curso> cursoOpt = cursoRepository.getById(cursoId);

        if (usuarioOpt.isEmpty() || cursoOpt.isEmpty()) return Optional.empty();

        if (carritoRepository.getByUsuarioIdAndCursoId(usuarioId, cursoId).isPresent()) {
            return Optional.empty();
        }

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuarioOpt.get());
        carrito.setCurso(cursoOpt.get());

        Carrito guardado = carritoRepository.save(carrito);
        return Optional.of(carritoMapper.toDTO(guardado));
    }

    @Transactional
    public boolean eliminarCursoDelCarrito(Long usuarioId, Long cursoId) {
        carritoRepository.deleteByUsuarioIdAndCursoId(usuarioId, cursoId);
        return true;
    }

    public boolean estaCursoEnCarrito(Long usuarioId, Long cursoId) {
        return carritoRepository.getByUsuarioIdAndCursoId(usuarioId, cursoId).isPresent();
    }

}