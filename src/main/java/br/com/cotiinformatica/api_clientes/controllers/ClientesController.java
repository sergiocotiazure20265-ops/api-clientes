package br.com.cotiinformatica.api_clientes.controllers;

import br.com.cotiinformatica.api_clientes.dtos.ClienteRequestDto;
import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.entities.Plano;
import br.com.cotiinformatica.api_clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ClienteRequestDto dto) {
        try {

            var cliente = new Cliente(); //instanciando a classe de entidade
            cliente.setPlano(new Plano()); //instanciando o atributo plano associado ao cliente

            cliente.setId(UUID.randomUUID()); //Gerando um ID para o cliente
            cliente.setNome(dto.nome()); //Capturando o nome enviado
            cliente.setEmail(dto.email()); //Capturando o email enviado
            cliente.setCpf(dto.cpf()); //Capturando o cpf enviado
            cliente.getPlano().setId(dto.planoId()); //Capturando o ID do plano enviado

            //Salvando no banco de dados
            clienteRepository.inserir(cliente);

            //HTTP 201 (CREATED)
            return ResponseEntity.status(201).body("Cliente cadastrado com sucesso.");
        }
        catch(Exception e) {
            //HTTP 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
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
