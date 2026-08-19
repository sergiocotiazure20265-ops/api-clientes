package br.com.cotiinformatica.api_clientes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Plano {

    private UUID id;
    private String nome;
    private String descricao;
    private Double valorMensal;

    @JsonIgnore
    private List<Cliente> clientes;
}
