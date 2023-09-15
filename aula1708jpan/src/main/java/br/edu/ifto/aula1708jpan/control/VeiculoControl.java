package br.edu.ifto.aula1708jpan.control;

import br.edu.ifto.aula1708jpan.model.entity.Veiculo;
import br.edu.ifto.aula1708jpan.model.repostories.VeiculoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping ("veiculo")
public class VeiculoControl {

    @Autowired
    private VeiculoRepositorio veiculoRepositorio;


    @GetMapping("/cadastrar")
    public ModelAndView cadastro(Veiculo veiculo) {
        ModelAndView modelAndView = new ModelAndView("veiculo/cadastro");
        modelAndView.addObject("veiculo", veiculo);
        return modelAndView;
    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("veiculo/lista");
        List<Veiculo> veiculos = veiculoRepositorio.veiculos();
        modelAndView.addObject("veiculos", veiculos);
        return modelAndView;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(Veiculo veiculo) {
        veiculoRepositorio.save(veiculo);
        return new ModelAndView("redirect:/veiculo/listar");
    }

    @GetMapping("/editar")
    public ModelAndView editar(@RequestParam("id") Long id) {
        Veiculo veiculo = veiculoRepositorio.veiculoFind(id);
            veiculoRepositorio.update(veiculo);
            ModelAndView modelAndView = new ModelAndView("veiculo/cadastro");
            return modelAndView.addObject("veiculo", veiculo);

    }
    @PostMapping("/update")
    public ModelAndView editar(@ModelAttribute("veiculo") Veiculo veiculo) {
        veiculoRepositorio.update(veiculo);
        return listar();
    }


    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        veiculoRepositorio.remove(id);
        return listar();
    }
}

