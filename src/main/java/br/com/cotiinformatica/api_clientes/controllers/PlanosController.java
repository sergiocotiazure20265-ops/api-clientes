package br.com.cotiinformatica.api_clientes.controllers;

import br.com.cotiinformatica.api_clientes.repositories.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/planos")
public class PlanosController {

    @Autowired
    private PlanoRepository planoRepository;

    @GetMapping
    public ResponseEntity<?> get() {
        try {
            //Consultando os planos no banco de dados atraves do repositório
            var planos = planoRepository.consultarTodos();
            //HTTP 200 (OK)
            return ResponseEntity.ok(planos);
        }
        catch(Exception e) {
            //HTTP 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
