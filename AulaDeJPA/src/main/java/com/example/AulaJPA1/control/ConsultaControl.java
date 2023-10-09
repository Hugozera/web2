package com.example.AulaJPA1.control;

import com.example.AulaJPA1.model.entity.Consulta;
import com.example.AulaJPA1.model.entity.Medico;
import com.example.AulaJPA1.model.entity.Paciente;
import com.example.AulaJPA1.model.repositories.ConsultaRepositorio;
import com.example.AulaJPA1.model.repositories.MedicoRepositorio;
import com.example.AulaJPA1.model.repositories.PacienteRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("consulta")
public class ConsultaControl {


    @Autowired
    private ConsultaRepositorio consultaRepositorio;
    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;


//    @GetMapping("/cadastro")
//    public ModelAndView cadastro(Consulta consulta) {
//        ModelAndView modelAndView = new ModelAndView("consulta/cadastro");
//        modelAndView.addObject("consulta", consulta);
//        return modelAndView;
//    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("consulta/lista");
        List<Consulta> consultas= consultaRepositorio.consultas();
        modelAndView.addObject("consultas", consultas);
        double valorTotal = consultas.stream().mapToDouble(consulta -> consulta.getValor()).sum();
        modelAndView.addObject("valorTotal", valorTotal);
        return modelAndView;
    }

    @GetMapping("/detalhes")
    public ModelAndView listarDetalhes(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("consulta/detalhes");
        List<Consulta> consultas= consultaRepositorio.detalhes(id);
        modelAndView.addObject("consultas", consultas);
        return modelAndView;
    }
    @GetMapping("/listaCpf")
    public ModelAndView listarCpf(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("consulta/lista");
        List<Consulta> consultas= consultaRepositorio.consultasPorIdPaciente(id);
        modelAndView.addObject("consultas", consultas);
        modelAndView.addObject("view", "paciente");
        double valorTotal = consultas.stream().mapToDouble(consulta -> consulta.getValor()).sum();
        modelAndView.addObject("valorTotal", valorTotal);
        return modelAndView;
    }
    @GetMapping("/listaCrm")
    public ModelAndView listarCrm(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("consulta/lista");
        List<Consulta> consultas= consultaRepositorio.consultasPorIdMedico(id);
        modelAndView.addObject("consultas", consultas);
        modelAndView.addObject("view", "medico");
        double valorTotal = consultas.stream().mapToDouble(consulta -> consulta.getValor()).sum();
        modelAndView.addObject("valorTotal", valorTotal);
        return modelAndView;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("consulta") Consulta consulta,
                               @RequestParam("medico.id") Long medicoId,
                               @RequestParam("paciente.id") Long pacienteId) {
        Medico medico = medicoRepositorio.medicoFind(medicoId);
        Paciente paciente = pacienteRepositorio.pacienteFind(pacienteId);

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        consultaRepositorio.save(consulta);

        return new ModelAndView("redirect:/consulta/listar");
    }

    @GetMapping("/editar")
    public ModelAndView editar(@RequestParam("id") Long id) {
        Consulta consulta = consultaRepositorio.consultaFind(id);
        ModelAndView modelAndView = new ModelAndView("consulta/cadastro");
        modelAndView.addObject("consulta", consulta);
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView editar(@ModelAttribute("consulta") Consulta consulta) {
        consultaRepositorio.update(consulta);
        return listar();
    }


    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        consultaRepositorio.remove(id);
        return listar();
    }

    @GetMapping("/buscar")
    public ModelAndView buscarMedicoEPaciente(
            @RequestParam(value = "crm", required = false) String crm,
            @RequestParam(value = "cpf", required = false) String cpf) {
        ModelAndView modelAndView = new ModelAndView("consulta/cadastro");

        if (crm != null) {
            Medico medicoEncontrado = medicoRepositorio.medicoFindCrm(crm);
            modelAndView.addObject("medicoEncontrado", medicoEncontrado);
        }

        if (cpf != null) {
            Paciente pacienteEncontrado = pacienteRepositorio.pacienteFindCpf(cpf);
            modelAndView.addObject("pacienteEncontrado", pacienteEncontrado);
        }

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView mostrarFormulario(Model model, Consulta consulta) {
        List<Medico> medicos = medicoRepositorio.medicos();
        List<Paciente> pacientes = pacienteRepositorio.pacientes();
        model.addAttribute("medicos", medicos);
        model.addAttribute("pacientes", pacientes);
        ModelAndView modelAndView = new ModelAndView("consulta/cadastro");
        modelAndView.addObject("consulta", consulta);
        return modelAndView;
    }

}

