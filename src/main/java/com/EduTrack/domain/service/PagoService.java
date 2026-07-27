package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.PagoDTO;
import com.EduTrack.domain.repository.*;
import com.EduTrack.persistence.entity.Curso;
import com.EduTrack.persistence.entity.CursoComprado;
import com.EduTrack.persistence.entity.Pago;
import com.EduTrack.persistence.entity.Usuarios;
import com.EduTrack.persistence.entity.EstadoPago;
import com.EduTrack.persistence.mapper.PagoMapper;
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

    @Autowired
    private EmailService emailService;

    @Transactional
    public Optional<PagoDTO> registrarPago(PagoDTO dto) {
        Optional<Usuarios> usuarioOpt = usuariosRepository.getById(dto.getUsuarioId());
        Optional<Curso> cursoOpt = cursoRepository.getById(dto.getCursoId());

        if (usuarioOpt.isEmpty() || cursoOpt.isEmpty()) {
            return Optional.empty();
        }

        if (pagoRepository.existePago(dto.getUsuarioId(), dto.getCursoId())) {
            return Optional.empty();
        }

        Pago pago = new Pago();
        pago.setUsuario(usuarioOpt.get());
        pago.setCurso(cursoOpt.get());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setReferenciaPago(dto.getReferenciaPago());
        pago.setFechaPago(LocalDateTime.now());
        pago.setEstado(EstadoPago.COMPLETADO);
        Pago guardado = pagoRepository.save(pago);

        carritoRepository.deleteByUsuarioIdAndCursoId(dto.getUsuarioId(), dto.getCursoId());

        if (!cursoCompradoRepository.existeCompra(dto.getUsuarioId(), dto.getCursoId())) {
            CursoComprado compra = new CursoComprado();
            compra.setUsuario(usuarioOpt.get());
            compra.setCurso(cursoOpt.get());
            compra.setFechaCompra(LocalDateTime.now());
            cursoCompradoRepository.save(compra);
        }

        String emailUsuario = usuarioOpt.get().getEmail();
        String nombreCurso = cursoOpt.get().getNombre();
        String mensaje = "Hola " + usuarioOpt.get().getNombre() + ",\n\n"
                + "¡Gracias por tu compra! Has adquirido el curso: " + nombreCurso + ".\n"
                + "Ya puedes acceder al contenido desde tu panel de estudiante.\n\n"
                + "Saludos,\nEduTrack";

        emailService.enviarCorreo(emailUsuario, "Confirmación de compra - EduTrack", mensaje);

        return Optional.of(pagoMapper.toDTO(guardado));
    }

    public boolean yaFuePagado(Long usuarioId, Long cursoId) {
        return pagoRepository.existePago(usuarioId, cursoId);
    }

    public List<PagoDTO> listarPagosPorUsuario(Long usuarioId) {
        List<Pago> pagos = pagoRepository.findByUsuarioId(usuarioId);
        return pagoMapper.toDTOList(pagos);
    }

    public List<PagoDTO> listarPagosPorCurso(Long cursoId) {
        List<Pago> pagos = pagoRepository.findByCursoId(cursoId);
        return pagoMapper.toDTOList(pagos);
    }
}