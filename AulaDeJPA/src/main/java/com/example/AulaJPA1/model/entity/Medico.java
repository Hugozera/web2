package com.example.AulaJPA1.model.entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
public class Medico  extends Pessoa{



    @Column(name = "crm")
    private String crm;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public String getCrm() {
        return super.getCrm();
    }

    @Override
    public void setCrm(String crm) {
        super.setCrm(crm);
    }
}
