
# Backend - Compartilha+

Este repositório contém o backend do projeto **COMPARTILHA+**.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Maven

## 📂 Estrutura do Projeto

```
src/
 └── main/
     ├── java/
     │   └── org/example/
     │       ├── config/
     │       ├── controller/
     │       ├── domain/
     │       ├── repository/
     │       ├── service/
     │       └── Main.java
     └── resources/
         ├── static
         └── templates
```

## Endpoints

| Método | Endpoint              | Descrição                       |
|--------|-----------------------|---------------------------------|
| GET    | `/` ou `/produto/listar` | Lista todos os produtos     |
| GET    | `/item-info/{id}`     | Exibe os detalhes de um produto |
| POST   | `/produto/cadastrar`  | Cadastra novo produto com imagem |

## Para clonar

### 1. Clone este repositório:
```bash
git clone https://github.com/Michele-Medeiros/compartilha-mais.git
```
## Para acessar
Acesse: `http://localhost:8080/`

### 2. Instale as dependências

Abra o terminal na raiz do projeto e execute:

```bash
mvn clean install
```

> Isso fará o download de todas as dependências necessárias (Spring Boot, Lombok, etc).

### 3. Compile e execute o projeto

```bash
mvn spring-boot:run
```

Ou, se preferir, execute diretamente pela sua IDE usando a classe `Main.java` (classe com a anotação `@SpringBootApplication`).

### 4. Acesse no navegador

Acesse:

```
http://localhost:8080/
```

Você verá a página inicial da aplicação.

---

## Banco de Dados

- Banco: **H2 Database**
- Tipo: **Em memória**
- Interface web disponível em:  
  ```
  http://localhost:8080/h2-console
  ```
- JDBC URL padrão:
  ```
  datasource:
    url: jdbc:h2:mem:compartilhadb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  ```
