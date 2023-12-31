package com.example.aula2.Control;
import com.example.aula2.DAO.PessoaDAO;
import com.example.aula2.DAO.ProdutoDAO;
import com.example.aula2.Model.Pessoa;
import com.example.aula2.Model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

    @Controller
    @RequestMapping("produto")
    public class ProdutoController {

        ProdutoDAO dao;

        public ProdutoController(){
            dao = new ProdutoDAO();
        }

        /**
         * @param produto necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
         * @return
         */
        @GetMapping("/form")
        public String form(Produto produto){
            return "/produto/form";
        }

        @GetMapping("/list")
        public ModelAndView listarProduto(ModelMap model) {
            model.addAttribute("produto", dao.buscarProduto());
            return new ModelAndView("/produto/list", model);
        }

        @PostMapping("/save")
        public ModelAndView saveProduto(Produto produto){
            dao.saveProduto(produto);
            return new ModelAndView("redirect:/produto/list");
        }

        /**
         * @param id
         * @return
         * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
         */
        @GetMapping("/removeProduto/{id}")
        public ModelAndView remove(@PathVariable("id") Long id){
            dao.removeProduto(id);
            return new ModelAndView("/produto/list");
        }

        /**
         * @param id
         * @return
         * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
         */
        @GetMapping("/editProduto/{id}")
        public ModelAndView editProduto(@PathVariable("id") Long id, ModelMap model) {
            model.addAttribute("produto", dao.buscarProduto(id));
            return new ModelAndView("produto/Form", model);
        }

        @PostMapping("/update")
        public ModelAndView updateProduto(Produto produto) {
            dao.updateProduto(produto);
            return new ModelAndView("redirect:/produto/list");
        }

}
