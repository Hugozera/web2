package br.edu.ifto.aula1708jpan.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Pessoa implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  long id;



}
