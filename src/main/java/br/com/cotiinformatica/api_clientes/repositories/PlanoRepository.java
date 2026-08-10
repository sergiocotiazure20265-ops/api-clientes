package br.com.cotiinformatica.api_clientes.repositories;

import br.com.cotiinformatica.api_clientes.entities.Plano;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PlanoRepository {

    @Autowired //Instanciar automaticamente
    private ConnectionFactory connectionFactory;

    /*
        Método para retornar uma lista dos planos cadastrados
     */
    public List<Plano> consultarTodos() throws Exception {

        try (var connection = connectionFactory.getConnection()) {

            //Consultando os planos no banco de dados
            var statement = connection.prepareStatement("SELECT * FROM planos ORDER BY nome");
            var result = statement.executeQuery();

            //Criando uma lista para armazenar os resultados obtidos na consulta
            List<Plano> lista = new ArrayList<>();

            //Percorrer os registros obtidos
            while(result.next()) {

                var plano = new Plano();

                plano.setId(UUID.fromString(result.getString("id")));
                plano.setNome(result.getString("nome"));
                plano.setDescricao(result.getString("descricao"));
                plano.setValorMensal(result.getDouble("valormensal"));

                lista.add(plano); //populando a lista
            }

            //Retornar a lista
            return lista;
        }
    }
}
