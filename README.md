# api-clientes

API Spring Boot para gerenciamento de clientes e planos.

Este repositório contém uma API REST construída com Spring Boot para gerenciar clientes e planos. O projeto foi estruturado de forma simples e modular, utilizando apenas JDBC para acesso ao banco de dados (sem Spring Data JPA) e contando com ferramentas que aceleram o desenvolvimento e a documentação da API.

## Tecnologias

- Spring Boot - https://spring.io/projects/spring-boot
- Spring Web - https://spring.io/projects/spring-web
- JDBC (Java Database Connectivity) - https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/
- Lombok - https://projectlombok.org/
- Swagger / OpenAPI - https://swagger.io/
- PostgreSQL - https://www.postgresql.org/
- Docker - https://www.docker.com/
- Docker Compose - https://docs.docker.com/compose/
- pgAdmin - https://www.pgadmin.org/

## Visão geral

A API expõe endpoints HTTP para operações relacionadas a clientes e planos (CRUD). A arquitetura é em camadas, o que ajuda a manter responsabilidades separadas, facilitar testes e evoluir o sistema.

> Observação: nesta documentação descrevemos a função de cada camada/pacote, sem detalhar as classes específicas dentro deles.

## Estrutura em camadas

O código está organizado nos seguintes pacotes (camadas):

- configurations
  - Contém classes de configuração da aplicação, como configuração do Swagger/OpenAPI, configuração de conexões com o banco, beans e outras configurações globais.

- controllers
  - Responsável por expor os endpoints HTTP (REST). Recebe requisições, valida/transforma entradas, delega a lógica para os serviços e retorna respostas HTTP adequadas.

- entities
  - Define as entidades do domínio (modelos) que representam clientes, planos e quaisquer outras estruturas persistidas no banco de dados.

- dtos
  - Contém objetos de transferência de dados (Data Transfer Objects) usados para receber e retornar payloads das APIs, separando o modelo de persistência da representação externa.

- services
  - Implementa a lógica de negócio da aplicação. Os services recebem dados dos controllers, aplicam regras de negócio, coordenam transações e chamam os repositórios quando necessário.

- repositories
  - Camada responsável pelo acesso ao banco de dados. Implementa operações SQL usando JDBC para inserir, atualizar, consultar e remover dados.

## Banco de dados

O banco de dados usado é PostgreSQL e é executado via Docker através do arquivo `docker-compose.yml` presente no repositório. O docker-compose também inclui o serviço pgAdmin para administração gráfica do banco.

Essa abordagem facilita o provisionamento do ambiente de banco de dados para desenvolvimento e testes, mantendo a infraestrutura isolada em contêineres.

## Links úteis

- Spring Boot: https://spring.io/projects/spring-boot
- Spring Web: https://spring.io/projects/spring-web
- JDBC: https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/
- Lombok: https://projectlombok.org/
- Swagger / OpenAPI: https://swagger.io/
- PostgreSQL: https://www.postgresql.org/
- Docker: https://www.docker.com/
- Docker Compose: https://docs.docker.com/compose/
- pgAdmin: https://www.pgadmin.org/

---
