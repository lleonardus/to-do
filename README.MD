# 👨‍💻‍Sobre o projeto

Esse projeto foi feito com o intuito de aprender mais sobre o [Spring Data MongoDB](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#reference).

Através dessa API é possível criar, buscar, atualizar e deletar tasks.

Para mais informações sobre as configurações do banco, acesse `src/main/resources/application.yml` ou 
`docker-compose.yml` (caso esteja usando Docker).

## 💿Como rodar na sua máquina

<details>
    <summary><b>🐳Utilizando Docker</b></summary>

**Pré-requisitos:**

- **Git**;
- **Docker + Docker-Compose**;

```shell
# Clone o repositório na sua máquina
$ git clone https://github.com/lleonardus/to-do.git

# Abra a pasta do projeto
$ cd to-do

# Inicie o projeto usando Docker
$ docker-compose up -d
```

Após esse processo, a API vai estar rodando em **http://localhost:8080**
e a documentação estará disponível em **http://localhost:8080/swagger-ui.html**.

</details>

<details>
    <summary><b>♨️Utilizando Maven</b></summary>

**Pré-requisitos:**

- **Git**;
- **Maven**
- **Java 17**
- **MongoDB**

```shell
# Clone o repositório na sua máquina
$ git clone https://github.com/lleonardus/to-do.git

# Abra a pasta do projeto
$ cd to-do

# Inicie o projeto usando Maven
$ mvn spring-boot:run
```

Após esse processo, a API vai estar rodando em **http://localhost:8080** e
a documentação estará disponível em **http://localhost:8080/swagger-ui.html**
</details>

Obs: Caso não queira utilizar nenhuma das opções acima, ainda é possível rodar o projeto utilizando
uma IDE da sua preferência (Intellij, Eclipse...)


## 🎯Endpoints

### 📜Tasks

- **GET** `/tasks`: Retorna uma lista com todas as tasks.
- **GET** `/tasks/status?completed=boolean`: Retorna uma lista de tasks baseada no status.
- **GET** `/tasks/{id}`: Retorna uma task em específico.
- **POST** `/tasks`: Registra uma nova task.
- **PUT** `/tasks/{id}`: Atualiza uma task.
- **DELETE** `/tasks/{id}`: Deleta uma task em específico.

## 🧰Ferramentas Utilizadas

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring Boot](https://spring.io/)
- [Spring Data MongoDB](https://docs.spring1.io/spring-data/mongodb/docs/current/reference/html/#reference)
- [MongoDB](https://www.mongodb.com/)
- [SpringDoc OpenAPI](https://springdoc.org/v2/)
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)