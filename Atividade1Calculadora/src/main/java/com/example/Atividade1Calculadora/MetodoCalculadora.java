package com.example.Atividade1Calculadora;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetodoCalculadora {

    @GetMapping("/tabuada")
    public String metodoCalc1(@RequestParam("valor") int valor) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i <= 10; i++) {
            int multiplicacao = valor * i;
            result.append(valor).append("*").append(i).append("=").append(multiplicacao).append("<br>");
        }
        return result.toString();
    }

}
