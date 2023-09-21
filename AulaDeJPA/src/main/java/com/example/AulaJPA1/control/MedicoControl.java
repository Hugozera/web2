package com.example.AulaJPA1.control;

import com.example.AulaJPA1.model.entity.Medico;
import com.example.AulaJPA1.model.repositories.MedicoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("medico")
public class MedicoControl {

    @Autowired
    private MedicoRepositorio medicoRepositorio;


    @GetMapping("/cadastrar")
    public ModelAndView cadastro(Medico medico) {
        ModelAndView modelAndView = new ModelAndView("medico/cadastro");
        modelAndView.addObject("meedico", medico);
        return modelAndView;
    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("medico/lista");
        List<Medico> medicos= medicoRepositorio.medicos();
        modelAndView.addObject("medicos", medicos);
        return modelAndView;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(Medico medico) {
        medicoRepositorio.save(medico);
        return new ModelAndView("redirect:/medico/listar");
    }

    @GetMapping("/editar")
    public ModelAndView editar(@RequestParam("id") Long id) {
        Medico medico = medicoRepositorio.medicoFind(id);
        medicoRepositorio.update(medico);
        ModelAndView modelAndView = new ModelAndView("medico/cadastro");
        return modelAndView.addObject("medico", medico);

    }
    @PostMapping("/update")
    public ModelAndView editar(@ModelAttribute("medico") Medico medico) {
        medicoRepositorio.update(medico);
        return listar();
    }


    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        medicoRepositorio.remove(id);
        return listar();
    }
}
