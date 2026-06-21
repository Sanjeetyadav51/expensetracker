# 💰 SpendSmart — Personal Finance Tracker

A full-stack expense and income tracking application built with Spring Boot and vanilla JavaScript. Users can sign up, securely log in, track income/expenses by category, and visualize spending patterns through interactive charts.

---

## 🛠️ Tech Stack

**Backend**
- Java 21
- Spring Boot 3.5
- Spring Security + JWT (jjwt)
- Spring Data JPA / Hibernate
- BCrypt password hashing

**Database**
- MySQL 8

**Frontend**
- HTML5, CSS3, Vanilla JavaScript (no framework — kept deliberately lightweight)
- Chart.js for data visualization

**Tools**
- Maven
- Postman (API testing)
- Git / GitHub

---

## ✨ Features

- **Authentication** — JWT-based signup/login with BCrypt password hashing and stateless session management
- **Transaction Management** — full CRUD for income and expense entries, scoped per authenticated user
- **Dashboard** — real-time totals for income, expenses, balance, and savings rate
- **Analytics** — category-wise spending breakdown, 6-month trend line, income vs. expense comparison, all rendered with Chart.js
- **Category System** — default categories (Food, Travel, Shopping, Bills, Healthcare, etc.) plus custom user-defined categories
- **Search & Filter** — filter transactions by type, category, or free-text search
- **User-Scoped Data Access** — every transaction is tied to its owner; the service layer explicitly verifies ownership before update/delete operations, preventing users from accessing each other's data

---

## 🏗️ Architecture

```
┌─────────────────┐       JWT Bearer Token       ┌──────────────────┐       JPA / Hibernate       ┌───────────┐
│  Frontend (JS)   │ ───────────────────────────► │  Spring Boot API  │ ───────────────────────────► │   MySQL   │
│  index.html      │ ◄─────────────────────────── │  REST Controllers │ ◄─────────────────────────── │           │
└─────────────────┘          JSON Responses       └──────────────────┘                              └───────────┘
```

Authentication flow: user submits credentials → Spring Security validates against BCrypt-hashed password → JWT issued → token attached to every subsequent request via `Authorization: Bearer <token>` header → `JwtFilter` validates the token on each protected request before it reaches the controller layer.

---

## 📁 Project Structure

```
com.sanjeet.expensetracker/
├── model/          # JPA entities — User, Transaction, Category
├── repository/      # Spring Data JPA repositories
├── service/          # Business logic layer
├── controller/      # REST API endpoints
├── security/         # JWT generation/validation, Spring Security config
└── dto/                  # Request/response payloads
```

---

## 🔌 API Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|--------------|----------------|
| POST | `/api/auth/signup` | Register a new user | No |
| POST | `/api/auth/login` | Authenticate and receive JWT | No |
| GET | `/api/transactions` | Get all transactions for logged-in user | Yes |
| POST | `/api/transactions` | Add a new transaction | Yes |
| PUT | `/api/transactions/{id}` | Update a transaction | Yes |
| DELETE | `/api/transactions/{id}` | Delete a transaction | Yes |

---

## 🚀 Running Locally

**Prerequisites:** Java 21, Maven, MySQL 8

1. Clone the repository
   ```bash
   git clone https://github.com/Sanjeetyadav51/spendsmart.git
   cd spendsmart
   ```

2. Create the database
   ```sql
   CREATE DATABASE expense_tracker_db;
   CREATE USER 'your_user'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON expense_tracker_db.* TO 'your_user'@'localhost';
   ```

3. Configure `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker_db
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   jwt.secret=your_secret_key_here
   ```

4. Run the application
   ```bash
   mvn spring-boot:run
   ```

5. Open `http://localhost:8080` in your browser

---

## 🔒 Security Notes

- Passwords are never stored in plaintext — hashed with BCrypt before persistence
- JWT tokens carry no sensitive data beyond the user's email and an expiry timestamp
- Stateless session management — no server-side session storage, scales horizontally by design
- Every transaction operation verifies the requesting user owns the resource before allowing modification

---

## 🧭 Roadmap

- [ ] Move category management to the backend (currently client-side only)
- [ ] Add backend input validation (Bean Validation annotations)
- [ ] Add JUnit + Mockito test coverage for service and controller layers
- [ ] Deploy backend (Render/Railway) and database (Railway/Aiven)
- [ ] Add budget alerts and recurring transaction support
- [ ] Export transaction history to PDF/Excel

---

## 👤 Author

**Sanjeet Yadav**
B.Tech CSE (AI), PSIT Kanpur
[GitHub](https://github.com/Sanjeetyadav51) · [LinkedIn](https://linkedin.com/in/sanjeetyadav)
