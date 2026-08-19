package br.com.cotiinformatica.api_clientes.repositories;

import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.entities.Plano;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ClienteRepository {

    @Autowired
    private ConnectionFactory connectionFactory;

    public void inserir(Cliente cliente) throws Exception {
        try (var connection = connectionFactory.getConnection()) {

            //Verificar se o CPF já está cadastrado
            var statement = connection.prepareStatement("""
                SELECT COUNT(*) as qtd 
                FROM clientes
                WHERE cpf = ?
            """);
            statement.setString(1, cliente.getCpf());
            var result = statement.executeQuery();

            //Capturando a quantidade obtida
            var qtd = 0;
            if(result.next()) {
                qtd = result.getInt("qtd");
            }

            if(qtd > 0) { //Se o cpf já esitiver cadastrado
                throw new IllegalArgumentException("O cpf informado já está cadastrado. Tente outro.");
            }

            statement = connection.prepareStatement("""
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
        try(var connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("""
                SELECT
                	c.id as idcliente,
                	c.nome as nomecliente,
                	c.email,
                	c.cpf,
                	p.id as idplano,
                	p.nome as nomeplano,
                	p.descricao,
                	p.valormensal
                FROM clientes c
                INNER JOIN planos p
                ON c.plano_id = p.id
                WHERE c.nome ILIKE ?
                AND c.ativo = true
                ORDER BY c.nome
            """);
            statement.setString(1, "%" + nome + "%");
            var result = statement.executeQuery();

            List<Cliente> clientes = new ArrayList<>();

            while(result.next()) {

                var cliente = new Cliente();

                cliente.setId((UUID) result.getObject("idcliente"));
                cliente.setNome(result.getString("nomecliente"));
                cliente.setEmail(result.getString("email"));
                cliente.setCpf(result.getString("cpf"));

                cliente.setPlano(new Plano());

                cliente.getPlano().setId((UUID) result.getObject("idplano"));
                cliente.getPlano().setNome(result.getString("nomeplano"));
                cliente.getPlano().setDescricao(result.getString("descricao"));
                cliente.getPlano().setValorMensal(result.getDouble("valormensal"));

                clientes.add(cliente);
            }

            return clientes;
        }
    }

    public Cliente obterPorId(UUID id) throws Exception {
        try(var connection = connectionFactory.getConnection()) {
            var statement = connection.prepareStatement("""
                SELECT
                	c.id as idcliente,
                	c.nome as nomecliente,
                	c.email,
                	c.cpf,
                	p.id as idplano,
                	p.nome as nomeplano,
                	p.descricao,
                	p.valormensal
                FROM clientes c
                INNER JOIN planos p
                ON c.plano_id = p.id
                WHERE c.id = ?
                AND c.ativo = true
            """);
            statement.setObject(1, id);
            var result = statement.executeQuery();

            if(result.next()) {

                var cliente = new Cliente();

                cliente.setId((UUID) result.getObject("idcliente"));
                cliente.setNome(result.getString("nomecliente"));
                cliente.setEmail(result.getString("email"));
                cliente.setCpf(result.getString("cpf"));

                cliente.setPlano(new Plano());

                cliente.getPlano().setId((UUID) result.getObject("idplano"));
                cliente.getPlano().setNome(result.getString("nomeplano"));
                cliente.getPlano().setDescricao(result.getString("descricao"));
                cliente.getPlano().setValorMensal(result.getDouble("valormensal"));

                return cliente;
            }

            return null;
        }
    }
}
