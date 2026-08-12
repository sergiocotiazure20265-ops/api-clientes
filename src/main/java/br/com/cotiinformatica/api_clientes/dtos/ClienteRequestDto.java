package br.com.cotiinformatica.api_clientes.dtos;

import java.util.UUID;

public record ClienteRequestDto(
        String nome,    //Nome do cliente
        String email,   //Email do cliente
        String cpf,     //CPF do cliente
        UUID planoId    //Id do plano selecionado
) {
}
