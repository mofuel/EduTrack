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

    public Asistencia guardar(Asistencia asistencia) {return asistenciaRepository.save(asistencia);}

    public void eliminar(Long id) {asistenciaRepository.deleteById(id);}
}
