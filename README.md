# CS2 Esports Hub — Endterm Project

## About the project

This project is a **CS2 Esports Hub backend application** developed using **Java, Spring Boot, JDBC, and PostgreSQL**.

The project is inspired by **HLTV** and the **Counter-Strike 2 ecosystem**.  
It stores and processes esports data such as teams, players, matches, articles, maps, and player inventories.

The project follows a **SQL-first approach**, starting with database design and later extending to a full **Spring Boot REST API** tested with Postman.

---

## Technologies used

- Java 17+
- Spring Boot (Web, JDBC)
- PostgreSQL
- JDBC with PreparedStatement
- Maven
- Postman
- Git / GitHub

---

## Database design

The database schema was designed before implementing Java code.

### Main tables:
- teams
- players
- matches
- tournaments
- articles
- tags
- article_tags (many-to-many)
- maps
- weapons
- skins
- player_inventory (many-to-many)

### Database features:
- Primary Keys (PK)
- Foreign Keys (FK)
- ON DELETE CASCADE / SET NULL
- CHECK constraints
- UNIQUE constraints
- Many-to-many relationships
- Real CS2 domain logic

All tables and constraints are defined in `schema.sql`.

---

## Test data

After schema creation, realistic test data was inserted:
- Professional CS2 teams
- Players and statistics
- Matches and tournaments
- CS2 maps (Mirage, Inferno, Nuke, Ancient, Anubis, etc.)
- Weapons and skins with rarity and market prices
- Player inventories

This allows all analytics and endpoints to work immediately.

---

## Project structure

src/main/java/org/example/endterm
│
├── controller // REST controllers
├── service // Business logic
├── repository // JDBC database access
├── model // Entities and DTOs
├── exception // Custom exceptions
├── utils // Database connection utilities
└── EndtermApplication.java
---

## JDBC and PreparedStatement

All database operations use **JDBC with PreparedStatement**, not ORM.

Reasons:
- Protection against SQL injection
- Explicit SQL control
- Clear understanding of database operations
- Compliance with course requirements

Each repository:
- Opens a database connection
- Executes SQL queries
- Maps ResultSet to Java objects
- Closes resources safely

---

## REST API

The application exposes REST endpoints using Spring Boot controllers.

### Example endpoints:

#### Teams
- GET `/teams` — get all teams
- POST `/teams` — create a new team

#### Players
- GET `/players`
- GET `/players/top`
- GET `/players/kills`

#### Articles
- GET `/articles`

#### Maps
- GET `/maps`

#### Inventory
- GET `/inventory/player/{id}`
- GET `/inventory/top`

All endpoints were tested using **Postman**.

---

## Business logic

The service layer implements:
- Top teams by rating
- Top players by rating
- Kill/Death (K/D) calculation
- Player inventory value calculation
- Top inventories by market value
- CS2 maps and active pool logic

---

## Articles system

Articles are implemented using **inheritance and abstraction**:
- Article (abstract class)
- CS2Article
- EsportsArticle

This allows polymorphic behavior and clean separation of article types.

---

## Error handling

Custom runtime exceptions are used to separate:
- Validation errors
- Database errors
- Business logic errors

This improves maintainability and debugging.

---

## How to run

1. Create PostgreSQL database
2. Run `schema.sql`
3. Configure database credentials
4. Open project in IntelliJ IDEA
5. Run `EndtermApplication`
6. Test endpoints using Postman or browser

---

## What I learned

- Relational database design
- JDBC and SQL integration
- Spring Boot REST architecture
- Layered backend development
- Working with real esports domain logic
- API testing with Postman

---

## Final note

This project demonstrates practical backend development skills, combining databases, JDBC, Spring Boot, and RESTful APIs in a real-world CS2 esports domain.
