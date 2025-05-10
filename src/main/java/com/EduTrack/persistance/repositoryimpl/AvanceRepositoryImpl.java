package com.EduTrack.persistance.repositoryimpl;

import com.EduTrack.persistance.entity.Avance;
import com.EduTrack.domain.repository.AvanceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvanceRepositoryImpl implements AvanceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Avance> findByUsuarioId(Long usuarioId) {
        return entityManager.createQuery("SELECT a FROM Avance a WHERE a.estudiante.id = :usuarioId", Avance.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    @Override
    @Transactional
    public Avance save(Avance avance) {
        entityManager.persist(avance);
        return avance;
    }
}
