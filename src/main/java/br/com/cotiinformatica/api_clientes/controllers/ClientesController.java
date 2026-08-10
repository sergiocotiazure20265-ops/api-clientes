package br.com.cotiinformatica.api_clientes.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientesController {

    @PostMapping
    public String post() {
        return "Cliente cadastrado com sucesso.";
    }

    @PutMapping
    public String put() {
        return "Cliente atualizado com sucesso.";
    }

    @DeleteMapping
    public String delete() {
        return "Cliente excluído com sucesso.";
    }

    @GetMapping
    public String get() {
        return "Consulta de clientes realizada com sucesso.";
    }
}
