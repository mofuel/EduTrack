package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.PagoDTO;
import com.EduTrack.domain.repository.*;
import com.EduTrack.persistance.entity.Curso;
import com.EduTrack.persistance.entity.CursoComprado;
import com.EduTrack.persistance.entity.Pago;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.persistance.mapper.PagoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CursoCompradoRepository cursoCompradoRepository;

    @Autowired
    private PagoMapper pagoMapper;

    @Transactional
    public Optional<PagoDTO> registrarPago(PagoDTO dto) {
        Optional<Usuarios> usuarioOpt = usuariosRepository.getById(dto.getUsuarioId());
        Optional<Curso> cursoOpt = cursoRepository.getById(dto.getCursoId());

        if (usuarioOpt.isEmpty() || cursoOpt.isEmpty()) {
            return Optional.empty();
        }

        // Evita pagos duplicados
        if (pagoRepository.existePago(dto.getUsuarioId(), dto.getCursoId())) {
            return Optional.empty();
        }

        // 1. Guardar el pago
        Pago pago = new Pago();
        pago.setUsuario(usuarioOpt.get());
        pago.setCurso(cursoOpt.get());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setReferenciaPago(dto.getReferenciaPago());
        pago.setFechaPago(LocalDateTime.now());

        Pago guardado = pagoRepository.save(pago);

        // 2. Eliminar del carrito
        carritoRepository.deleteByUsuarioIdAndCursoId(dto.getUsuarioId(), dto.getCursoId());

        // 3. Registrar curso comprado (si no fue comprado aún)
        if (!cursoCompradoRepository.existeCompra(dto.getUsuarioId(), dto.getCursoId())) {
            CursoComprado compra = new CursoComprado();
            compra.setUsuario(usuarioOpt.get());
            compra.setCurso(cursoOpt.get());
            compra.setFechaCompra(LocalDateTime.now());
            cursoCompradoRepository.save(compra);
        }

        return Optional.of(pagoMapper.toDTO(guardado));
    }


    public boolean yaFuePagado(String usuarioId, Long cursoId) {
        return pagoRepository.existePago(usuarioId, cursoId);
    }

    public List<PagoDTO> listarPagosPorUsuario(String usuarioId) {
        List<Pago> pagos = pagoRepository.findByUsuarioId(usuarioId);
        return pagoMapper.toDTOList(pagos);
    }

    public List<PagoDTO> listarPagosPorCurso(Long cursoId) {
        List<Pago> pagos = pagoRepository.findByCursoId(cursoId);
        return pagoMapper.toDTOList(pagos);
    }
}
