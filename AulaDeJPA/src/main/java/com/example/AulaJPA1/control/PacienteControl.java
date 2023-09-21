package com.example.AulaJPA1.control;


import com.example.AulaJPA1.model.entity.Paciente;
import com.example.AulaJPA1.model.repositories.PacienteRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("paciente")
public class PacienteControl {

    @Autowired
    private PacienteRepositorio pacienteRepositorio;


    @GetMapping("/cadastrar")
    public ModelAndView cadastro(Paciente paciente) {
        ModelAndView modelAndView = new ModelAndView("paciente/cadastro");
        modelAndView.addObject("paciente", paciente);
        return modelAndView;
    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("paciente/lista");
        List<Paciente> pacientes= pacienteRepositorio.pacientes();
        modelAndView.addObject("pacientes", pacientes);
        return modelAndView;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(Paciente paciente) {
        pacienteRepositorio.save(paciente);
        return new ModelAndView("redirect:/paciente/listar");
    }

    @GetMapping("/editar")
    public ModelAndView editar(@RequestParam("id") Long id) {
        Paciente paciente = pacienteRepositorio.pacienteFind(id);
        pacienteRepositorio.update(paciente);
        ModelAndView modelAndView = new ModelAndView("paciente/cadastro");
        return modelAndView.addObject("paciente", paciente);

    }
    @PostMapping("/update")
    public ModelAndView editar(@ModelAttribute("consulta")  Paciente paciente) {
        pacienteRepositorio.update(paciente);
        return listar();
    }


    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        pacienteRepositorio.remove(id);
        return listar();
    }
}

