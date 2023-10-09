package com.example.AulaJPA1.model.repositories;

import com.example.AulaJPA1.model.entity.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ConsultaRepositorio {


    @PersistenceContext
    private EntityManager em ;

    public void save(Consulta consulta){
        consulta.setData(LocalDateTime.now());
        em.persist(consulta);
    }

    public Consulta consultaFind (Long id){
        return em.find(Consulta.class, id);
    }

    public List<Consulta> consultas(){
        Query query = em.createQuery("from Consulta");
        return query.getResultList();
    }
    public List<Consulta> detalhes(Long id){
        Query query = em.createQuery("from Consulta where id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }
    public List<Consulta> consultasPorIdPaciente(Long id) {
        Query  query = em.createQuery("SELECT c FROM Consulta c WHERE c.paciente.id = :id", Consulta.class);
        query.setParameter("id",id);
        return query.getResultList();
    }
    public List<Consulta> consultasPorIdMedico(Long id) {
        Query query = em.createQuery("SELECT c FROM Consulta c WHERE c.medico.id = :id", Consulta.class);
        query.setParameter("id", id);
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
