package com.example.aulaJPA.model.repositories;

import com.example.aulaJPA.model.entity.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultaRepositorio {


    @PersistenceContext
    private EntityManager em ;

    public void save(Consulta consulta){
        em.persist(consulta);
    }

    public Consulta veiculoFind (Long id){
        return em.find(Consulta.class, id);
    }

    public List<Consulta> Consultas(){
        Query query = em.createQuery("from consulta");
        return query.getResultList();
    }

    public void remove(Long id){
        Consulta c = em.find(Consulta.class, id);
        em.remove(c);
    }

    public void update(Consulta consulta){
        em.merge(consulta);
    }
}
