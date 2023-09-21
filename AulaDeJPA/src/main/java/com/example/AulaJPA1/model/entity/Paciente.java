package com.example.AulaJPA1.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Paciente extends Pessoa {

    @Column(name = "cpf")
    private String cpf;

    @Override
    public String getCpf() {
        return super.getCpf();
    }

    @Override
    public void setCpf(String cpf) {
        super.setCpf(cpf);
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

}
