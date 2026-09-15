# SpendSmart — Personal Finance Tracker 💰

SpendSmart is a full-stack personal finance management application that helps users track income, expenses, transactions, and spending patterns through an interactive dashboard.

The application is built with **Spring Boot, Spring Security, JWT, MySQL, HTML, CSS, JavaScript, and Chart.js** and is deployed using **Docker on Render with MySQL hosted on Aiven**.

## 🌐 Live Demo

🔗 https://expensetracker-6y0h.onrender.com

> Note: The application is hosted on free-tier infrastructure, so the first request after a period of inactivity may take some time.

---

## ✨ Features

### 🔐 Authentication
- User registration
- User login
- JWT-based authentication
- Secure password handling
- Session persistence using browser local storage

### 📊 Dashboard
- Total income
- Total expenses
- Current balance
- Savings rate
- Recent transactions
- Interactive charts

### 💳 Transaction Management
- Add income and expenses
- Edit transactions
- Delete transactions
- Transaction categories
- Transaction date and notes
- Search transactions
- Filter by transaction type
- Filter by category

### 📈 Analytics
- Highest expense category
- Average daily spending
- Total number of transactions
- Monthly spending trend
- Income vs expense comparison
- Category-wise spending breakdown

### 🏷️ Categories
- Default expense categories
- Custom categories
- Category-based transaction filtering
- Custom categories stored per user in browser local storage

### 👤 Profile
- View user information
- Update displayed name and email
- User-specific profile information

---

## 🛠️ Tech Stack

### Backend
- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Spring Security
- JWT Authentication
- Bean Validation
- Maven

### Frontend
- HTML5
- CSS3
- JavaScript
- Chart.js

### Database
- MySQL 8.4
- Aiven MySQL

### Deployment
- Docker
- Render
- Aiven

### Version Control
- Git
- GitHub

---

## 🏗️ Architecture

```text
                         ┌──────────────────────┐
                         │       Browser        │
                         │                      │
                         │ HTML / CSS / JS      │
                         │      Chart.js        │
                         └──────────┬───────────┘
                                    │
                                    │ REST API
                                    ▼
                         ┌──────────────────────┐
                         │       Render         │
                         │                      │
                         │ Docker Container     │
                         │      Spring Boot     │
                         │                      │
                         │ Spring Security      │
                         │ JWT Authentication   │
                         │ Spring Data JPA      │
                         └──────────┬───────────┘
                                    │
                                    │ JDBC
                                    ▼
                         ┌──────────────────────┐
                         │      Aiven MySQL     │
                         │                      │
                         │ Users                │
                         │ Transactions         │
                         │ Categories           │
                         └──────────────────────┘
User
 │
 │ Login / Signup
 ▼
Spring Boot API
 │
 │ Validate credentials
 ▼
JWT Token
 │
 ▼
Browser localStorage
 │
 │ Authorization: Bearer <token>
 ▼
Protected API Endpoints

## 👤 Author

**Sanjeet Yadav**
B.Tech CSE (AI), PSIT Kanpur
[GitHub](https://github.com/Sanjeetyadav51) · [LinkedIn](https://linkedin.com/in/sanjeetyadav)
