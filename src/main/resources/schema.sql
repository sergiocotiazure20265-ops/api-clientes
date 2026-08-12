-- Criar a tabela de planos
CREATE TABLE planos(
    id              UUID            PRIMARY KEY,
    nome            VARCHAR(50)     NOT NULL UNIQUE,
    descricao       VARCHAR(250)    NOT NULL,
    valormensal     NUMERIC(10, 2)  NOT NULL
);

-- Inserindo planos na tabela
INSERT INTO planos(id, nome, descricao, valormensal)
    VALUES
        (gen_random_uuid(), 'Básico', 'Plano básico para clientes', 29.90),
        (gen_random_uuid(), 'Intermediário', 'Plano com mais recursos', 59.90),
        (gen_random_uuid(), 'Premium', 'Plano completo para clientes', 99.90);

-- Consultando os planos
SELECT * FROM planos;

-- Criar a tabela de clientes
CREATE TABLE clientes(
    id                      UUID            PRIMARY KEY,
    nome                    VARCHAR(150)    NOT NULL,
    email                   VARCHAR(50)     NOT NULL,
    cpf                     VARCHAR(11)     NOT NULL UNIQUE,
    datahoracadastro        TIMESTAMP       NOT NULL,
    datahoraalteracao       TIMESTAMP       NULL,
    datahoraexclusao        TIMESTAMP       NULL,
    ativo                   BOOLEAN         DEFAULT TRUE,
    plano_id                UUID            NOT NULL,
    FOREIGN KEY(plano_id) REFERENCES planos(id)
);



