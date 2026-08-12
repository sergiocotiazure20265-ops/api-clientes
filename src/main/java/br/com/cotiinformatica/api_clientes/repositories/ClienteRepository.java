package br.com.cotiinformatica.api_clientes.repositories;

import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ClienteRepository {

    @Autowired
    private ConnectionFactory connectionFactory;

    public void inserir(Cliente cliente) throws Exception {
        try (var connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("""
                INSERT INTO clientes (id, nome, email, cpf, plano_id, datahoracadastro)
                VALUES(?, ?, ?, ?, ?, CURRENT_TIMESTAMP)
            """);
            statement.setObject(1, cliente.getId());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getCpf());
            statement.setObject(5, cliente.getPlano().getId());
            statement.execute();
        }
    }

    public boolean atualizar(Cliente cliente) throws Exception {
        try (var connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("""
                UPDATE clientes 
                SET
                   nome = ?,
                   email = ?,
                   cpf = ?,
                   plano_id = ?,
                   datahoraalteracao = CURRENT_TIMESTAMP
                WHERE
                    id = ?
                AND
                    ativo = TRUE
            """);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getCpf());
            statement.setObject(4, cliente.getPlano().getId());
            statement.setObject(5, cliente.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean excluir(UUID id) throws Exception {
        try (var connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("""
                UPDATE clientes 
                SET
                   ativo = FALSE
                   datahoraexclusao = CURRENT_TIMESTAMP
                WHERE
                    id = ?
                AND
                    ativo = TRUE
            """);
            statement.setObject(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public List<Cliente> obterPorNome(String nome) throws Exception {
        //TODO
        return null;
    }

    public Cliente obterPorId(UUID id) throws Exception {
        //TODO
        return null;
    }
}
