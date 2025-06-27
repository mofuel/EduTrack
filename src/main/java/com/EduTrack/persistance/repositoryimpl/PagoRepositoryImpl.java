package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.domain.repository.PagoRepository;
import com.EduTrack.persistance.crud.PagoCrudRepository;
import com.EduTrack.persistance.entity.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PagoRepositoryImpl implements PagoRepository {

    @Autowired
    private PagoCrudRepository crud;

    @Override
    public List<Pago> findByUsuarioId(String usuarioId) {
        return crud.findByUsuario_Id(usuarioId);
    }

    @Override
    public List<Pago> findByCursoId(Long cursoId) {
        return crud.findByCurso_Id(cursoId);
    }

    @Override
    public boolean existePago(String usuarioId, Long cursoId) {
        return crud.existsByUsuario_IdAndCurso_Id(usuarioId, cursoId);
    }

    @Override
    public Pago save(Pago pago) {
        return crud.save(pago);
    }

    @Override
    public Optional<Pago> findById(Long id) {
        return crud.findById(id);
    }

}
