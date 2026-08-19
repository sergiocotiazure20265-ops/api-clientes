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

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable UUID id, @RequestBody ClienteRequestDto dto) {

        try {
            //Buscar o cliente no banco de dados através do ID..
            var cliente = clienteRepository.obterPorId(id);

            //Se nenhum cliente for encontrado
            if(cliente == null) {
                //HTTP 404 (NOT FOUND)
                return ResponseEntity.status(404).body("Cliente não encontrado para edição.");
            }

            //Modificar os dados do cliente
            cliente.setNome(dto.nome());
            cliente.setEmail(dto.email());
            cliente.setCpf(dto.cpf());
            cliente.getPlano().setId(dto.planoId());

            //Atualizando no banco de dados
            clienteRepository.atualizar(cliente);

            //HTTP 200 (OK)
            return ResponseEntity.status(200).body("Cliente atualizado com sucesso.");
        }
        catch (Exception e) {
            //HTTP 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        try {

            var cliente = clienteRepository.obterPorId(id);

            if(cliente == null) {
                //HTTP 404 (NOT FOUND)
                return ResponseEntity.status(404).body("Cliente não encontrado para exclusão.");
            }

            clienteRepository.excluir(id);

            //HTTP 200 (OK)
            return ResponseEntity.status(200).body("Cliente excluído com sucesso.");
        }
        catch(Exception e) {
            //HTTP 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getByNome(@RequestParam String nome) {

        try {

            var clientes = clienteRepository.obterPorNome(nome);

            //HTTP 200 (OK)
            return ResponseEntity.status(200).body(clientes);
        }
        catch(Exception e) {
            //HTTP 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

        try {

            var cliente = clienteRepository.obterPorId(id);

            if(cliente == null) {
                //HTTP 404 (NOT FOUND)
                return ResponseEntity.status(404).body("Cliente não encontrado.");
            }

            //HTTP 200 (OK)
            return ResponseEntity.status(200).body(cliente);
        }
        catch(Exception e) {
            //HTTP 500 (INTERNAL SERVER ERROR)
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }
}
