# 📝 ToDo List API

API RESTful para gerenciamento de tarefas com **Spring Boot 3**, **validações**, **testes automatizados** e persistência em **MySQL** (produção) ou **H2** (testes).

## 🛠 Tecnologias

[![Java](https://img.shields.io/badge/Java-17-%23ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-%236DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-3.2-%236DB33F?logo=spring&logoColor=white)](https://spring.io/projects/spring-data-jpa)
[![Hibernate](https://img.shields.io/badge/Hibernate-6.4-%2359666C?logo=hibernate&logoColor=white)](https://hibernate.org)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-%234479A1?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![H2](https://img.shields.io/badge/H2_Database-2.2-%232C3E50?logo=h2&logoColor=white)](https://www.h2database.com)
[![Maven](https://img.shields.io/badge/Maven-3.9-%23C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org)

## 🚀 Tecnologias
- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL / H2 (testes)
- Lombok (se aplicável)
- WebTestClient (testes)

## 📋 Endpoints

| Método | Rota         | Descrição                          |
|--------|--------------|-----------------------------------|
| POST   | `/todos`     | Cria uma nova tarefa              |
| GET    | `/todos`     | Lista tarefas (ordenadas)         |
| PUT    | `/todos`     | Atualiza uma tarefa               |
| DELETE | `/todos/{id}`| Remove uma tarefa                 |

```mermaid
    %%{init: {'theme': 'neutral'}}%%
classDiagram
    direction TB
    
    %% Entidade Principal
    class Todo {
        +Long id
        +String nome
        +String descricao
        +boolean realizado
        +int prioridade
        +construtor()
        +getters/setters()
    }

    %% Camada de Persistência
    class TodoRepository {
        <<Interface>>
        +JpaRepository~Todo, Long~
    }

    %% Camada de Serviço
    class TodoService {
        -TodoRepository todoRepository
        +create(Todo todo) List~Todo~
        +list() List~Todo~
        +update(Todo todo) List~Todo~
        +delete(Long id) List~Todo~
    }

    %% Camada de Controller
    class TodoController {
        -TodoService todoService
        +create(@Valid Todo todo) List~Todo~
        +list() List~Todo~
        +update(Todo todo) List~Todo~
        +delete(Long id) List~Todo~
    }

    %% Classe de Configuração
    class DesafioTodolistApplication {
        +main(String[] args)
    }

    %% Testes
    class DesafioTodolistApplicationTests {
        -WebTestClient webTestClient
        +testCreateTodoSucess()
        +testCreateTodoFailure()
    }

    %% Relacionamentos
    TodoController --> TodoService : Depende
    TodoService --> TodoRepository : Depende
    TodoRepository --> Todo : Operações JPA
    DesafioTodolistApplicationTests --> TodoController : Testa endpoints
    DesafioTodolistApplication ..> TodoRepository : Inicializa contexto
```
### 🔄 Ordenação
As tarefas são retornadas ordenadas por:
1. **Prioridade** (decrescente)
2. **Nome** (crescente)

## ⚙️ Configuração

### 🛠️ Produção (MySQL)
Edite `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todolist
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
```
## 🧪 Testes (H2)

Configuração automática em src/test/resources/application-test.properties:
spring.datasource.url=jdbc:h2:mem:testdb

## 🧪 Testes de integração com WebTestClient:

@Test
void testCreateTodoSuccess() {
    webTestClient
        .post().uri("/todos")
        .bodyValue(new Todo("Estudar", "Spring Boot", false, 1))
        .exchange()
        .expectStatus().isOk();
}

## ▶️ Como Executar

```
# Produção (MySQL)
mvn spring-boot:run

# Testes
mvn test
```
## 📦 Exemplo de JSON

Request (POST /todos):
```
{
  "nome": "Estudar JPA",
  "descricao": "Praticar relações",
  "realizado": false,
  "prioridade": 2
}
```
## 📌 Validações

- @NotBlank nos campos nome e descricao
- Retorno HTTP 400 para dados inválidos

# Autor
[Alexsandro Almeida](https://linkedin.com/in/alexsandro-j-a-almeida)
