package br.com.cotiinformatica.api_clientes.entities;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Plano {

    private UUID id;
    private String nome;
    private String descricao;
    private Double valorMensal;
    private List<Cliente> clientes;
}
