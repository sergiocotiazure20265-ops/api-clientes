package br.com.cotiinformatica.api_clientes.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Cliente {

    private UUID id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDateTime dataHoraCadastro;
    private LocalDateTime dataHoraAlteracao;
    private LocalDateTime dataHoraExclusao;
    private Boolean ativo;
    private Plano plano;
}
