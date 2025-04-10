
# 💻 Backend - Compartilha+

Este repositório contém o backend do projeto **COMPARTILHA+**.

## 🔧 Tecnologias Utilizadas

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

## 📌 Endpoints

| Método | Endpoint              | Descrição                       |
|--------|-----------------------|---------------------------------|
| GET    | `/produtos`           | Lista todos os produtos         |
| GET    | `/item-info/{id}`     | Retorna detalhes do produto     |
| POST   | `/produtos`           | Cadastra novo produto           |

## Para clonar

Clone este repositório:
```bash
git clone https://github.com/Michele-Medeiros/compartilha-mais.git
```
## para acessar
Acesse: `http://localhost:8080/`

## 🛠 Banco de Dados

O projeto utiliza H2 para testes locais.


