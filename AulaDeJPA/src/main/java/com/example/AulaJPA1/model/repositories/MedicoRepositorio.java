package com.example.AulaJPA1.model.repositories;

import com.example.AulaJPA1.model.entity.Medico;
import com.example.AulaJPA1.model.entity.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepositorio {

    @PersistenceContext
    private EntityManager em ;

    public void save(Medico medico){
        em.persist(medico);
    }

    public Medico medicoFind (Long id){
        return em.find(Medico.class, id);
    }

    public List<Medico> medicos(){
        Query query = em.createQuery("from Medico");
        return query.getResultList();
    }

    public void remove(Long id){
        Medico m = em.find(Medico.class, id);
        em.remove(m);
    }
    public Medico medicoFindCrm(String crm){
        return em.find(Medico.class, crm);
    }

    public void update(Medico medico){
        em.merge(medico);
    }
}
