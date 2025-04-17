
# Compartilha+

**Compartilha+** é uma aplicação web colaborativa desenvolvida em Java com Spring Boot e Thymeleaf. Seu objetivo é facilitar o compartilhamento de recursos, produtos e serviços entre usuários.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Thymeleaf
- Lombok
- Maven

---

## Requisitos do Sistema

| Tipo de Instalação | Requisitos |
|--------------------|------------|
| `.exe` | Windows 10+ *(não requer Java)* |
| `.jar` | Java 17+ instalado manualmente |
| Ambos | Navegador atualizado (Chrome, Firefox, Edge) |

---

## Instalação e Execução

### Opção 1: Executável `.exe` (Windows)

1. Baixe o arquivo `CompartilhaPlus.exe`
2. Clique duas vezes no executável
3. Aguarde a inicialização
4. Acesse: [http://localhost:8080](http://localhost:8080)

> ℹ️ Esta opção **não requer Java instalado**, pois o JRE pode estar embutido.

### Opção 2: Arquivo `.jar` (Windows, Mac e Linux)

> ⚙️ Esta opção é multiplataforma e exige **Java 17+ instalado**.

#### ✅ Pré-requisito
Instale o Java 17 a partir do [Adoptium](https://adoptium.net/temurin/releases/) ou com os comandos abaixo:

##### Mac (via Homebrew)
```bash
brew install openjdk@17
brew link --force --overwrite openjdk@17
```

##### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

#### ▶️ Executar a aplicação
Após instalar o Java, execute:

```bash
java -jar compartilha-plus-1.0.0.jar
```

Em seguida, acesse via navegador:

```
http://localhost:8080
```

---

## Como Acessar

Acesse via navegador:

```
http://localhost:8080
```

> Para mudar a porta, edite o arquivo `application.properties`:
**Exemplo:**
```properties
server.port=9090
```

---

## Funcionalidades da Aplicação

| Funcionalidade          | Descrição |
|--------------------------|-----------|
| 🏠 Página Inicial         | Visão geral e navegação inicial |
| 👤 Cadastro de Usuários   | Criação de contas com nome, e-mail e senha |
| 🔐 Login                 | Acesso autenticado ao sistema |
| 📦 Cadastro de Itens     | Registro de produtos, serviços ou recursos |
| 🔍 Busca de Itens        | Filtros por nome, tipo ou categoria |
| 🛒 Solicitar Recurso     | Envio de solicitações de uso de itens |
| 🧾 Histórico             | Visualização de solicitações feitas e recebidas |

---

## Endpoints REST

| Método | Endpoint            | Descrição                       |
|--------|---------------------|---------------------------------|
| GET    | `/produtos`         | Lista todos os produtos         |
| GET    | `/item-info/{id}`   | Detalhes de um produto          |
| POST   | `/produtos`         | Cadastra novo produto           |

---

## Estrutura do Projeto

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
         ├── static/
         └── templates/
```

---

## Dúvidas

### A aplicação funciona offline?
Sim! Após o download, ela roda localmente.

### Posso mudar a porta da aplicação?
Sim. Edite `application.properties` e defina a porta desejada:
**Exemplo:**
```properties
server.port=9090
```

---

## Como Clonar

```bash
git clone https://github.com/Michele-Medeiros/compartilha-mais.git
cd compartilha-mais
```
