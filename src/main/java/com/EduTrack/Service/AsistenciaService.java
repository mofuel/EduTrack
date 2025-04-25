package com.EduTrack.Service;

import com.EduTrack.Model.Asistencia;
import com.EduTrack.Repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    @Autowired
    public AsistenciaService(AsistenciaRepository asistenciaRepository) {this.asistenciaRepository = asistenciaRepository;}

    public List<Asistencia> obtenerTodas() {return asistenciaRepository.findAll();}

    public Optional<Asistencia> obtenerPorId(Long id) {return asistenciaRepository.findById(id);}

    public Asistencia guardar(Asistencia asistencia) {
        // Verificar si ya existe una asistencia para esa fecha y curso
        Optional<Asistencia> asistenciaExistente = asistenciaRepository
                .findByFechaAndCurso(asistencia.getFecha(), asistencia.getCurso());

        if (asistenciaExistente.isPresent()) {
            // Si ya existe, puedes devolver la asistencia existente
            return asistenciaExistente.get();
        }

        // Asignar la asistencia a cada detalle si no existe
        if (asistencia.getDetalles() != null) {
            for (var detalle : asistencia.getDetalles()) {
                detalle.setAsistencia(asistencia);
            }
        }

        return asistenciaRepository.save(asistencia);
    }


    public void eliminar(Long id) {asistenciaRepository.deleteById(id);}
}
