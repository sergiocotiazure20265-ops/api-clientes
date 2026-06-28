// Importando o Express
const express = require('express');

// Importando o Swagger UI
const swaggerUi = require('swagger-ui-express');

// Criando a aplicação Express
const app = express();

// Configurando a API para receber JSON no corpo das requisições
app.use(express.json());

// Definindo a porta da aplicação
const PORT = 3000;

// Base de dados mockada em memória
let clientes = [
  {
    id: 1,
    nome: 'João da Silva',
    email: 'joao@email.com',
    telefone: '(21) 99999-1111',
    dataCadastro: new Date()
  },
  {
    id: 2,
    nome: 'Maria Oliveira',
    email: 'maria@email.com',
    telefone: '(21) 99999-2222',
    dataCadastro: new Date()
  }
];

// Controle simples para gerar IDs
let proximoId = 3;

// Documentação Swagger
const swaggerDocument = {
  openapi: '3.0.0',
  info: {
    title: 'API de Clientes',
    description: 'API CRUD mockada de clientes usando Node.js, Express e Swagger',
    version: '1.0.0'
  },
  servers: [
    {
      url: 'http://localhost:3000',
      description: 'Servidor local'
    }
  ],
  tags: [
    {
      name: 'Clientes',
      description: 'Operações relacionadas ao cadastro de clientes'
    }
  ],
  paths: {
    '/clientes': {
      get: {
        tags: ['Clientes'],
        summary: 'Consultar todos os clientes',
        description: 'Retorna uma lista com todos os clientes cadastrados.',
        responses: {
          200: {
            description: 'Lista de clientes retornada com sucesso',
            content: {
              'application/json': {
                schema: {
                  type: 'array',
                  items: {
                    $ref: '#/components/schemas/Cliente'
                  }
                }
              }
            }
          }
        }
      },
      post: {
        tags: ['Clientes'],
        summary: 'Cadastrar um novo cliente',
        description: 'Cadastra um novo cliente na base mockada.',
        requestBody: {
          required: true,
          content: {
            'application/json': {
              schema: {
                $ref: '#/components/schemas/ClienteRequest'
              },
              example: {
                nome: 'Carlos Souza',
                email: 'carlos@email.com',
                telefone: '(21) 99999-3333'
              }
            }
          }
        },
        responses: {
          201: {
            description: 'Cliente cadastrado com sucesso',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/schemas/Cliente'
                }
              }
            }
          },
          400: {
            description: 'Dados inválidos'
          }
        }
      }
    },
    '/clientes/{id}': {
      get: {
        tags: ['Clientes'],
        summary: 'Consultar cliente por ID',
        description: 'Retorna os dados de um cliente específico pelo ID.',
        parameters: [
          {
            name: 'id',
            in: 'path',
            required: true,
            description: 'ID do cliente',
            schema: {
              type: 'integer',
              example: 1
            }
          }
        ],
        responses: {
          200: {
            description: 'Cliente encontrado com sucesso',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/schemas/Cliente'
                }
              }
            }
          },
          404: {
            description: 'Cliente não encontrado'
          }
        }
      },
      put: {
        tags: ['Clientes'],
        summary: 'Atualizar cliente',
        description: 'Atualiza os dados de um cliente existente.',
        parameters: [
          {
            name: 'id',
            in: 'path',
            required: true,
            description: 'ID do cliente',
            schema: {
              type: 'integer',
              example: 1
            }
          }
        ],
        requestBody: {
          required: true,
          content: {
            'application/json': {
              schema: {
                $ref: '#/components/schemas/ClienteRequest'
              },
              example: {
                nome: 'João da Silva Atualizado',
                email: 'joao.atualizado@email.com',
                telefone: '(21) 98888-1111'
              }
            }
          }
        },
        responses: {
          200: {
            description: 'Cliente atualizado com sucesso',
            content: {
              'application/json': {
                schema: {
                  $ref: '#/components/schemas/Cliente'
                }
              }
            }
          },
          400: {
            description: 'Dados inválidos'
          },
          404: {
            description: 'Cliente não encontrado'
          }
        }
      },
      delete: {
        tags: ['Clientes'],
        summary: 'Excluir cliente',
        description: 'Remove um cliente da base mockada pelo ID.',
        parameters: [
          {
            name: 'id',
            in: 'path',
            required: true,
            description: 'ID do cliente',
            schema: {
              type: 'integer',
              example: 1
            }
          }
        ],
        responses: {
          204: {
            description: 'Cliente excluído com sucesso'
          },
          404: {
            description: 'Cliente não encontrado'
          }
        }
      }
    }
  },
  components: {
    schemas: {
      Cliente: {
        type: 'object',
        properties: {
          id: {
            type: 'integer',
            example: 1
          },
          nome: {
            type: 'string',
            example: 'João da Silva'
          },
          email: {
            type: 'string',
            example: 'joao@email.com'
          },
          telefone: {
            type: 'string',
            example: '(21) 99999-1111'
          },
          dataCadastro: {
            type: 'string',
            format: 'date-time',
            example: '2026-06-28T10:00:00.000Z'
          }
        }
      },
      ClienteRequest: {
        type: 'object',
        required: ['nome', 'email', 'telefone'],
        properties: {
          nome: {
            type: 'string',
            example: 'João da Silva'
          },
          email: {
            type: 'string',
            example: 'joao@email.com'
          },
          telefone: {
            type: 'string',
            example: '(21) 99999-1111'
          }
        }
      }
    }
  }
};

// Configurando a rota do Swagger
app.use('/swagger', swaggerUi.serve, swaggerUi.setup(swaggerDocument));

// Rota inicial da API
app.get('/', (req, res) => {
  res.json({
    mensagem: 'API de Clientes está em execução',
    swagger: 'http://localhost:3000/swagger'
  });
});

// GET /clientes
// Consulta todos os clientes
app.get('/clientes', (req, res) => {
  res.status(200).json(clientes);
});

// GET /clientes/:id
// Consulta um cliente pelo ID
app.get('/clientes/:id', (req, res) => {
  const id = Number(req.params.id);

  const cliente = clientes.find(cliente => cliente.id === id);

  if (!cliente) {
    return res.status(404).json({
      mensagem: 'Cliente não encontrado'
    });
  }

  res.status(200).json(cliente);
});

// POST /clientes
// Cadastra um novo cliente
app.post('/clientes', (req, res) => {
  const { nome, email, telefone } = req.body;

  if (!nome || !email || !telefone) {
    return res.status(400).json({
      mensagem: 'Nome, email e telefone são obrigatórios'
    });
  }

  const novoCliente = {
    id: proximoId++,
    nome,
    email,
    telefone,
    dataCadastro: new Date()
  };

  clientes.push(novoCliente);

  res.status(201).json(novoCliente);
});

// PUT /clientes/:id
// Atualiza os dados de um cliente
app.put('/clientes/:id', (req, res) => {
  const id = Number(req.params.id);

  const { nome, email, telefone } = req.body;

  if (!nome || !email || !telefone) {
    return res.status(400).json({
      mensagem: 'Nome, email e telefone são obrigatórios'
    });
  }

  const cliente = clientes.find(cliente => cliente.id === id);

  if (!cliente) {
    return res.status(404).json({
      mensagem: 'Cliente não encontrado'
    });
  }

  cliente.nome = nome;
  cliente.email = email;
  cliente.telefone = telefone;

  res.status(200).json(cliente);
});

// DELETE /clientes/:id
// Exclui um cliente pelo ID
app.delete('/clientes/:id', (req, res) => {
  const id = Number(req.params.id);

  const clienteExiste = clientes.some(cliente => cliente.id === id);

  if (!clienteExiste) {
    return res.status(404).json({
      mensagem: 'Cliente não encontrado'
    });
  }

  clientes = clientes.filter(cliente => cliente.id !== id);

  res.status(204).send();
});

// Inicializando o servidor
app.listen(PORT, () => {
  console.log(`API rodando em http://localhost:${PORT}`);
  console.log(`Swagger disponível em http://localhost:${PORT}/swagger`);
});