
# API da Plataforma [Bunchin](https://github.com/MarcosAlves90/bunchin)

![GitHub repo size](https://img.shields.io/github/repo-size/MarcosAlves90/bunchin-api-java?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/MarcosAlves90/bunchin-api-java?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/MarcosAlves90/bunchin-api-java?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/MarcosAlves90/bunchin-api-java?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/MarcosAlves90/bunchin-api-java?style=for-the-badge)
![GitHub License](https://img.shields.io/github/license/MarcosAlves90/bunchin-api-java?style=for-the-badge)
![GitHub last commit](https://img.shields.io/github/last-commit/MarcosAlves90/bunchin-api-java?style=for-the-badge)
![Open Source Love](https://img.shields.io/badge/Open%20Source-%E2%9D%A4-red?style=for-the-badge)

## Descrição

O **bunchin-api-java** é uma API REST desenvolvida em Java com Spring Boot, voltada para o gerenciamento de funcionários e controle de pontos em ambientes corporativos. A plataforma oferece funcionalidades completas para cadastro, autenticação e administração de usuários, registro de pontos, além de recursos de recuperação e redefinição de senha. O sistema foi projetado para ser seguro, escalável e de fácil integração com outros sistemas, utilizando práticas modernas como cache para otimização de consultas e integração com banco de dados PostgreSQL. Ideal para empresas que buscam automatizar e centralizar o controle de presença e acesso de colaboradores.

## Funcionalidades Principais

- **CRUD de Funcionários:** Cadastro, consulta, atualização e remoção de funcionários.
- **Login:** Autenticação de usuários por e-mail ou CPF e senha.
- **Gerenciamento de Pontos:** Registro e controle de pontos dos funcionários.
- **Recuperação e Redefinição de Senha:** Geração de links de redefinição, verificação de códigos e alteração de senha.
- **Cache de Funcionários:** Otimização de consultas utilizando cache.

## Tecnologias Utilizadas

![Java Version](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen?style=for-the-badge&logo=springboot)
![Docker](https://img.shields.io/badge/Docker-ready-blue?style=for-the-badge&logo=docker)

- Spring Data JPA
- Spring Security
- PostgreSQL
- Maven

## Pré-requisitos

- Docker
- Docker Compose (opcional)

## Instalação e Execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/MarcosAlves90/bunchin-api-java.git
   ```

2. Configure as variáveis de ambiente necessárias em um arquivo `.env` (opcional) ou edite o `application.properties` conforme necessário.

3. Para construir e rodar a aplicação, utilize o Docker:

   ```bash
   docker build -t bunchin-api .
   docker run -p 8080:8080 --env-file .env bunchin-api
   ```

   > Certifique-se de que o banco de dados PostgreSQL esteja acessível e configurado conforme as variáveis de ambiente.

## Endpoints Principais

### Funcionários

- `GET /api/funcionario` — Lista todos os funcionários
- `GET /api/funcionario/{cpf}` — Busca funcionário por CPF
- `POST /api/funcionario` — Cria um novo funcionário
- `PUT /api/funcionario/{cpf}` — Atualiza um funcionário
- `DELETE /api/funcionario/{cpf}` — Remove um funcionário

### Login

- `POST /api/login` — Realiza login com e-mail ou CPF e senha

### Pontos

- `GET /api/ponto` — Lista todos os pontos
- `POST /api/ponto` — Cria um novo ponto

### Recuperação de Senha

- `POST /api/storeResetCode` — Armazena código de redefinição
- `POST /api/checkEmailExists` — Verifica se o e-mail existe
- `POST /api/verifyResetCode` — Verifica validade do código
- `PUT /api/resetPassword` — Redefine a senha
- `PUT /api/newPassword` — Define nova senha

## Exemplo de Requisição de Login

```json
POST /api/login
{
  "email": "usuario@dominio.com",
  "senha": "senha123"
}
```

## Estrutura do Projeto

- `src/main/java/br/com/fatecmaua/bunchin/control` — Controllers REST
- `src/main/java/br/com/fatecmaua/bunchin/model` — Entidades JPA
- `src/main/java/br/com/fatecmaua/bunchin/repository` — Repositórios JPA
- `src/main/java/br/com/fatecmaua/bunchin/service` — Serviços e cache
- `src/main/resources` — Configurações e recursos estáticos

```mermaid
erDiagram
    FUNCIONARIO {
        INTEGER n_registro PK
        STRING nome
        STRING email
        STRING senha
        STRING cpf
        STRING funcao
        STRING cargo
        STRING departamento
        STRING status
    }
    PONTO {
        UUID id_ponto PK
        STRING nome_tipo
        INSTANT data_hora
        STRING funcionario_fk FK
    }
    LINK {
        INTEGER id PK
        STRING email
        STRING codigo
        INSTANT data_criacao
        INTEGER funcionario_id FK
    }

    FUNCIONARIO ||..o{ PONTO : "registra"
    FUNCIONARIO ||..o{ LINK : "possui"
```

## Testes

Para rodar os testes automatizados:

```bash
docker run bunchin-api mvn test
```

## Docker

O projeto já possui um `Dockerfile` configurado para construção e execução da aplicação. Utilize o comando abaixo para construir e rodar:

```bash
docker build -t bunchin-api .
docker run -p 8080:8080 --env-file .env bunchin-api
```

## Contribuição

Pull requests são bem-vindos! Para grandes mudanças, abra uma issue primeiro para discutir o que você gostaria de modificar.

## Licença

Este projeto está sob a licença MIT.
