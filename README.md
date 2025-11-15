# 💎 Gold Cosmetics E-Commerce System

A full-stack web application for managing multi-shop beauty retail operations with online shopping, inventory management, and payment integration.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 🌟 Overview

Gold Cosmetics is a modern e-commerce platform designed for beauty retail businesses with multiple physical locations. The system supports online shopping, in-store point-of-sale operations, inventory management across shops, and comprehensive admin analytics.

**Current Features (v1.0):**
- ✅ Responsive homepage with shop locations
- ✅ About page
- ✅ Modern, mobile-friendly UI with Bootstrap
- ✅ Database integration with PostgreSQL

**Coming Soon:**
- 🔜 User authentication (Customer, Employee, Admin roles)
- 🔜 Product catalog with search and filtering
- 🔜 Shopping cart and checkout
- 🔜 M-Pesa payment integration via Paystack
- 🔜 Order tracking
- 🔜 Employee POS system
- 🔜 Admin analytics dashboard

## ✨ Features

### For Customers
- Browse products by category and shop location
- Add items to cart and checkout securely
- Pay via M-Pesa (Paystack integration)
- Track order status in real-time
- View purchase history
- Download digital receipts

### For Employees
- Manage inventory (add, update, delete products)
- Process in-store sales with POS interface
- Update online order statuses
- Track stock levels per shop
- All actions logged for audit trail

### For Administrators
- Full CRUD operations on all entities
- View sales analytics with charts
- Monitor profit per shop
- Track employee activities
- Manage user accounts and permissions
- Export reports

## 🛠️ Tech Stack

**Backend:**
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA (Hibernate)
- Spring Security
- PostgreSQL 16

**Frontend:**
- Thymeleaf Template Engine
- Bootstrap 5.3
- HTML5, CSS3, JavaScript

**Tools & Services:**
- Maven (Build Tool)
- Git (Version Control)
- Paystack API (Payments)
- Railway.app (Deployment)

## 🚀 Getting Started

### Prerequisites

- Java JDK 17 or higher
- Maven 3.6+
- PostgreSQL 16+
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR-USERNAME/gold-cosmetics.git
   cd gold-cosmetics
   ```

2. **Set up the database**
   ```bash
   # Start PostgreSQL
   sudo service postgresql start
   
   # Create database
   sudo -u postgres psql
   CREATE DATABASE gold_cosmetics;
   CREATE USER your_user WITH PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE gold_cosmetics TO your_user;
   \q
   ```

3. **Configure application properties**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/gold_cosmetics
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   ```

4. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access the application**
   
   Open your browser and navigate to: `http://localhost:8080`

## 📁 Project Structure

```
gold-cosmetics/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── goldcosmetics/
│   │   │           ├── GoldCosmeticsApplication.java
│   │   │           ├── controller/
│   │   │           │   └── HomeController.java
│   │   │           ├── model/            # (Coming soon)
│   │   │           ├── repository/       # (Coming soon)
│   │   │           └── service/          # (Coming soon)
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── home.html
│   │       │   └── about.html
│   │       ├── static/                   # (CSS, JS, images)
│   │       └── application.properties
│   └── test/                             # (Unit tests coming soon)
├── pom.xml
├── .gitignore
└── README.md
```

## 📸 Screenshots

### Homepage
![Homepage](screenshots/home.png)
*Beautiful, responsive homepage showcasing both shop locations*

### About Page
![About](screenshots/about.png)
*Learn about Gold Cosmetics mission and values*

*(More screenshots coming as features are developed)*

## 🗺️ Roadmap

### Phase 1: Foundation ✅ (Current)
- [x] Project setup
- [x] Database connection
- [x] Homepage and About page
- [x] Responsive design

### Phase 2: User Management (In Progress)
- [ ] User registration
- [ ] User login/logout
- [ ] Role-based access control
- [ ] Password encryption

### Phase 3: Product Management
- [ ] Product CRUD operations
- [ ] Category management
- [ ] Product images upload
- [ ] Inventory tracking per shop

### Phase 4: Shopping & Checkout
- [ ] Product browsing and search
- [ ] Shopping cart
- [ ] Checkout flow
- [ ] Paystack integration (M-Pesa)

### Phase 5: Order Management
- [ ] Order creation and tracking
- [ ] Employee order processing
- [ ] Delivery status updates
- [ ] Email notifications

### Phase 6: POS System
- [ ] In-store sales interface
- [ ] Receipt generation
- [ ] Inventory deduction
- [ ] Daily sales reports

### Phase 7: Analytics Dashboard
- [ ] Sales charts (pie, bar, line)
- [ ] Profit calculations
- [ ] Employee performance metrics
- [ ] Export reports (PDF, Excel)

### Phase 8: Deployment
- [ ] Security hardening
- [ ] Performance optimization
- [ ] Railway.app deployment
- [ ] Custom domain setup

## 🤝 Contributing

This is a learning project, but suggestions and feedback are welcome!

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Contact

**Angela** - Developer

- GitHub: [@YOUR-USERNAME](https://github.com/YOUR-USERNAME)
- Email: your.email@example.com

**Project Link:** [https://github.com/YOUR-USERNAME/gold-cosmetics](https://github.com/YOUR-USERNAME/gold-cosmetics)

---

## 🙏 Acknowledgments

- Built as a portfolio project to demonstrate full-stack development skills
- Inspired by the need for modern e-commerce solutions for small businesses
- Special thanks to the Spring Boot and Bootstrap communities

---

## 📊 Project Stats

- **Development Start:** November 2025
- **Current Version:** 1.0.0 (Foundation)
- **Lines of Code:** ~500+ (and growing!)
- **Commits:** Check the [commit history](https://github.com/YOUR-USERNAME/gold-cosmetics/commits/main)

---

<p align="center">Built with 💛 and ☕ by Angela</p>
<p align="center">Transforming Gold Cosmetics into a modern digital retail experience</p>
