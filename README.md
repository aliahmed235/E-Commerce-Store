# E-Commerce Store API

## Project Description

### Overview
A Spring Boot REST API for a fully functional e-commerce platform with user authentication, shopping cart management, product catalog, and Stripe payment integration.

## Core Features

### 🔐 Authentication & Authorization
- User registration and secure login with email/password
- JWT-based stateless authentication (access + refresh tokens)
- Role-based access control (USER, ADMIN roles)
- Refresh token stored in HTTP-only cookies for enhanced security
- Password encryption using BCrypt

### 🛒 Shopping Cart
- Add/remove products from cart
- Cart persistence in database
- Cart management with item quantities
- Automatic cart clearing after successful checkout

### 📦 Product Management
- Browse product catalog
- View detailed product information
- Add products to wishlist
- Admin capabilities for product management

### 💳 Payment Processing
- Stripe payment gateway integration
- Secure checkout session creation
- Webhook handling for real-time payment status updates
- Order status tracking (PENDING → PAID/FAILED/CANCELLED)
- Transaction security and error handling

### 📋 Order Management
- View complete order history with customer details
- Access individual order information with associated items
- Track order status and payment information
- User-specific order filtering and authorization

## Technology Stack

| Component | Technology |
|-----------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3.4.1 |
| Database | MySQL 8.0+ |
| ORM | JPA/Hibernate |
| Authentication | JWT (jjwt 0.12.6) |
| Payments | Stripe API |
| Migrations | Flyway |
| Mapping | MapStruct |
| Documentation | OpenAPI/Swagger |
| Build Tool | Maven |
| Security | Spring Security 6 |

## Getting Started

### Prerequisites
- Java 21 or higher
- MySQL 8.0 or higher
- Maven 3.6+
- Stripe API keys

### Installation

1. Clone the repository
```bash
git clone https://github.com/aliahmed235/E-Commerce-Store.git
cd E-Commerce-Store