package com.EduTrack.Service;

import com.EduTrack.Model.AsistenciaDetalle;
import com.EduTrack.Repository.AsistenciaDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaDetalleService {

    private final AsistenciaDetalleRepository asistenciaDetalleRepository;

    @Autowired
    public AsistenciaDetalleService(AsistenciaDetalleRepository asistenciaDetalleRepository) {this.asistenciaDetalleRepository = asistenciaDetalleRepository;}

    public List<AsistenciaDetalle> obtenerDetallesPorAsistencia(Long asistenciaId) {
        return asistenciaDetalleRepository.findAll().stream()
                .filter(detalle -> detalle.getAsistencia().getId().equals(asistenciaId))
                .toList();
}

    public Optional<AsistenciaDetalle> obtenerPorId(Long id) {return asistenciaDetalleRepository.findById(id);}

    public AsistenciaDetalle guardar(AsistenciaDetalle asistenciaDetalle) {return asistenciaDetalleRepository.save(asistenciaDetalle);}

    public void eliminar(Long id) {asistenciaDetalleRepository.deleteById(id);}
}
