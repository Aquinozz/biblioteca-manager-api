# 📚 API Biblioteca manager

API REST para gerenciamento de **livros, vendas e dados estatísticos**, com autenticação via JWT e controle de acesso por roles.

---

## 🚀 Tecnologias

* Java 21
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA
* H2 Database
* Swagger (OpenAPI)

---

## 🔐 Autenticação

A API utiliza autenticação baseada em **JWT (Bearer Token)**.

### ▶️ Login

**POST** `/auth/login`

```json
{
  "email": "admin@email.com",
  "senha": "123456"
}
```

**Resposta:**

```json
{
  "token": "SEU_TOKEN_AQUI",
  "expiresIn": 900000
}
```

---

## 🔑 Como usar o token

1. Faça login no endpoint `/auth/login`
2. Copie o token retornado
3. No Swagger, clique em **Authorize 🔒**
4. Cole o token no formato:

```
Bearer SEU_TOKEN_AQUI
```

---

## 👥 Roles

A API possui controle de acesso com duas roles:

* `ROLE_ADMIN`
* `ROLE_LEITOR`

### 📌 Permissões

| Endpoint      | ADMIN | LEITOR |
| ------------- | ----- | ------ |
| Criar livro   | ✔️    | ❌      |
| Listar livros | ✔️    | ✔️     |
| Criar venda   | ✔️    | ❌     |
| Deletar venda | ✔️    | ❌      |

---

## 📘 Endpoints

### 🔐 Autenticação

* **POST** `/auth/register` → Registrar usuário
* **POST** `/auth/login` → Realizar login

---

### 📚 Livros

* **GET** `/livros` → Listar livros
* **POST** `/livros` → Criar livro
* **PUT** `/livros/{id}` → Atualizar livro
* **DELETE** `/livros/{id}` → Deletar livro

---

### 💰 Vendas

* **GET** `/vendas` → Listar vendas
* **POST** `/vendas` → Criar venda
* **DELETE** `/vendas/{id}` → Cancelar venda

---

### 📊 Dados

* **GET** `/dados` → Resumo de vendas/lucro
* **GET** `/dados/exportar` → Exportar dados (PDF/XML)

---

## 🛠️ Banco de Dados

Banco em memória usando H2.

### Acesso:

```
http://localhost:8080/h2-console
```

---

## ⚙️ Usuário padrão

Criado automaticamente ao iniciar a aplicação:

```
email: admin@email.com
senha: 123456
```

---

## ▶️ Como executar

```bash
mvn spring-boot:run
```

---

## 📄 Documentação Swagger

Acesse:

```
http://localhost:8080/swagger-ui/index.html
```

---

## ⚠️ Observações

* Todas as rotas (exceto `/auth/**`) exigem autenticação
* Utilize sempre o prefixo `Bearer ` no token
* Roles seguem o padrão `ROLE_`
* Tokens expiram (faça login novamente se necessário)

---

## 👨‍💻 Autor

Murilo Aquino

