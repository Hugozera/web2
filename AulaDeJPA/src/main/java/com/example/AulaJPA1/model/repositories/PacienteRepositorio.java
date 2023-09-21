package com.example.AulaJPA1.model.repositories;

import com.example.AulaJPA1.model.entity.Paciente;
import com.example.AulaJPA1.model.entity.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepositorio {

    @PersistenceContext
    private EntityManager em ;

    public void save(Paciente paciente){
        Pessoa pessoa = paciente;
        em.persist(pessoa);
        em.persist(paciente);
    }

    public Paciente pacienteFind (Long id){
        return em.find(Paciente.class, id);
    }

    public List<Paciente> pacientes(){
        Query query = em.createQuery("from Paciente");
        return query.getResultList();
    }

    public void remove(Long id){
        Paciente p = em.find(Paciente.class, id);
        em.remove(p);
    }
    public Paciente pacienteFindCpf(String cpf){
        return em.find(Paciente.class, cpf);
    }

    public void update(Paciente paciente){
        em.merge(paciente);
    }
}
