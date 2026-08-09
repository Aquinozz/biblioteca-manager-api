# 📚 Biblioteca Manager API

REST API for managing **books, sales and statistical data**, with JWT authentication and role-based access control.

---

## 🚀 Technologies

* Java 21
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA
* H2 Database
* Swagger (OpenAPI)
* Slf4j

---

## 🔐 Authentication

The API uses **JWT (Bearer Token)** based authentication.

### ▶️ Login

**POST** `/auth/login`

```json
{
  "email": "admin@email.com",
  "senha": "123456"
}
```

**Response:**

```json
{
  "token": "YOUR_TOKEN_HERE",
  "expiresIn": 900000
}
```

---

## 🔑 How to use the token

1. Log in at the `/auth/login` endpoint
2. Copy the returned token
3. In Swagger, click on **Authorize 🔒**
4. Paste the token in the format:

```
Bearer YOUR_TOKEN_HERE (Depending on the swagger version, writing "Bearer" is not necessary)
```

---

## 👥 Roles

The API has access control with two roles:

* `ROLE_ADMIN`
* `ROLE_LEITOR`

### 📌 Permissions

| Endpoint      | ADMIN | LEITOR |
| ------------- | ----- | ------ |
| Create book   | ✔️    | ❌      |
| List books    | ✔️    | ✔️     |
| Create sale   | ✔️    | ❌     |
| Delete sale   | ✔️    | ❌      |

---

## 📘 Endpoints

### 🔐 Authentication

* **POST** `/auth/register` → Register user
* **POST** `/auth/login` → Log in

---

### 📚 Books

* **GET** `/livros` → List books
* **POST** `/livros` → Create book
* **PUT** `/livros/{id}` → Update book
* **DELETE** `/livros/{id}` → Delete book

---

### 💰 Sales

* **GET** `/vendas` → List sales
* **POST** `/vendas` → Create sale
* **DELETE** `/vendas/{id}` → Cancel sale

---

### 📊 Data

* **GET** `/dados` → Sales/profit summary
* **GET** `/dados/exportar` → Export data (PDF/XML)

---

## 🛠️ Database

In-memory database using H2.

### Access:

```
http://localhost:8080/h2-console
```

---

## ⚙️ Default user

Created automatically when the application starts:

```
email: admin@email.com
senha: 123456
```

---

## ▶️ How to run

```bash
mvn spring-boot:run
```

---

## 📄 Swagger Documentation

Access:

```
http://localhost:8080/swagger-ui/index.html
```

---

## ⚠️ Notes

* All routes (except `/auth/**`) require authentication
* Always use the `Bearer ` prefix in the token
* Roles follow the `ROLE_` pattern
* Tokens expire (log in again if necessary)

---

## 👨‍💻 Author

Murilo Aquino