# Account Service

Projeto simples de teste para gerenciamento de **contas bancárias** e **transferências** entre elas. Desenvolvido com Spring Boot como exercício de CRUD, camadas e integração com MySQL.

## Funcionalidades

- Criar conta com titular e saldo inicial
- Listar transferências realizadas
- Transferir valor entre duas contas
- Excluir conta por ID

## Tecnologias

- Java 21
- Spring Boot 4.2
- Spring Data JPA
- MySQL
- Lombok
- Maven

## Estrutura do projeto

```
src/main/java/raphael/account_service/
├── controller/     # Endpoints REST
├── service/        # Regras de negócio
├── repository/     # Acesso ao banco (JPA)
├── entity/         # Entidades mapeadas para o MySQL
├── dto/            # Objetos de entrada e saída da API
├── mapper/         # Conversão entre entity e DTO
└── enums/          # Status das transferências
```

## Pré-requisitos

- Java 21+
- Maven 3.9+
- MySQL em execução

## Configuração

1. Clone o repositório e entre na pasta do projeto.

2. Crie o banco de dados no MySQL:

```sql
CREATE DATABASE teste2;
```

3. Copie o arquivo de exemplo e configure as variáveis de ambiente:

```bash
cp .env.example .env
```

4. Edite o `.env` com suas credenciais locais. Use o `.env.example` como referência das variáveis necessárias.

> O arquivo `.env` não é versionado (está no `.gitignore`). As configurações são carregadas automaticamente via [spring-dotenv](https://github.com/paulschwarz/spring-dotenv).

## Como executar

```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

Para compilar sem executar:

```bash
mvn compile
```

## API

Base URL: `http://localhost:8080`

### Criar conta

`POST /transfers/account`

```json
{
  "owner": "João Silva",
  "balance": 1000.00
}
```

**Resposta (201):**

```json
{
  "id": 1,
  "owner": "João Silva",
  "balance": 1000.00
}
```

### Listar transferências

`GET /transfers`

**Resposta (200):**

```json
[
  {
    "fromAccount": { "id": 1, "owner": "João Silva", "balance": 900.00 },
    "toAccount": { "id": 2, "owner": "Maria Souza", "balance": 600.00 },
    "amount": 100.00,
    "status": "COMPLETED"
  }
]
```

### Criar transferência

`POST /transfers`

```json
{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 100.00
}
```

**Resposta (200):**

```json
{
  "fromAccount": { "id": 1, "owner": "João Silva", "balance": 900.00 },
  "toAccount": { "id": 2, "owner": "Maria Souza", "balance": 600.00 },
  "amount": 100.00,
  "status": "COMPLETED"
}
```

### Excluir conta

`DELETE /transfers/account/{id}`

**Resposta (200):**

```
Conta deletada com sucesso
```

## Regras de negócio

- A conta de origem e a de destino devem ser diferentes
- A conta de origem precisa existir e ter saldo suficiente
- Ao transferir, o valor é debitado da origem e creditado no destino
- Transferências bem-sucedidas recebem status `COMPLETED`

## Modelo de dados

### accounts

| Campo   | Tipo          |
|---------|---------------|
| id      | BIGINT (PK)   |
| owner   | VARCHAR       |
| balance | DECIMAL       |

### transfers

| Campo            | Tipo          |
|------------------|---------------|
| id               | BIGINT (PK)   |
| from_account_id  | BIGINT (FK)   |
| to_account_id    | BIGINT (FK)   |
| amount           | DECIMAL       |
| status           | VARCHAR       |

Status possíveis: `PENDING`, `COMPLETED`, `FAILED`

## Observações

Este é um projeto de **estudo e testes**, sem autenticação, tratamento avançado de erros ou deploy em produção. Use apenas para fins de aprendizado.
