package com.EduTrack.domain.service;
import com.EduTrack.domain.dto.CarritoDTO;
import com.EduTrack.domain.repository.CarritoRepository;
import com.EduTrack.persistance.entity.Carrito;
import com.EduTrack.persistance.entity.Curso;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.persistance.mapper.CarritoMapper;
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

    public List<CarritoDTO> listarCarritoPorUsuario(String usuarioId) {
        List<Carrito> carrito = carritoRepository.getByUsuarioId(usuarioId);
        return carritoMapper.toDTOList(carrito);
    }

    public Optional<CarritoDTO> agregarCursoAlCarrito(String usuarioId, Long cursoId) {
        Optional<Usuarios> usuarioOpt = usuarioRepository.getById(usuarioId);
        Optional<Curso> cursoOpt = cursoRepository.getById(cursoId);

        if (usuarioOpt.isEmpty() || cursoOpt.isEmpty()) return Optional.empty();

        // Verificar si ya está en el carrito
        if (carritoRepository.getByUsuarioIdAndCursoId(usuarioId, cursoId).isPresent()) {
            return Optional.empty(); // Ya está en el carrito
        }

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuarioOpt.get());
        carrito.setCurso(cursoOpt.get());

        Carrito guardado = carritoRepository.save(carrito);
        return Optional.of(carritoMapper.toDTO(guardado));
    }

    @Transactional
    public boolean eliminarCursoDelCarrito(String usuarioId, Long cursoId) {
        carritoRepository.deleteByUsuarioIdAndCursoId(usuarioId, cursoId);
        return true;
    }

    public boolean estaCursoEnCarrito(String usuarioId, Long cursoId) {
        return carritoRepository.getByUsuarioIdAndCursoId(usuarioId, cursoId).isPresent();
    }

}
