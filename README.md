CS2 Esports Hub — Endterm Project
About the project

This project is a CS2 Esports Hub backend application built using Java, Spring Boot, JDBC and PostgreSQL.

The idea of the project is inspired by HLTV and the CS2 ecosystem, including:

professional teams

players and statistics

esports articles

CS2 maps

player inventories (skins & weapons)

analytics and rankings

The project was developed step by step, starting from pure SQL and JDBC, and later extended to a Spring Boot REST API with Postman testing.

Technologies used

Java 17+

Spring Boot (Web, JDBC)

PostgreSQL

JDBC + PreparedStatement

Maven

Postman

Git / GitHub

Database design (SQL-first approach)

The project started with database schema design before writing Java code.

Main tables:

teams

players

matches

tournaments

articles

tags

article_tags (many-to-many)

maps

weapons

skins

player_inventory (many-to-many)

Database features:

Primary Keys (PK)

Foreign Keys (FK)

ON DELETE CASCADE / SET NULL

CHECK constraints

UNIQUE constraints

Many-to-many relationships

Real CS2 domain logic (maps, inventory, skins)

All tables and constraints are defined in schema.sql.

Test data

After schema creation, realistic test data was inserted:

CS2 teams (Vitality, FaZe, Falcons, Spirit, FURIA)

Professional players

Matches and tournaments

CS2 maps (Mirage, Inferno, Nuke, Ancient, Anubis, etc.)

Weapons and skins with rarity and market prices

Player inventories

This allows analytics and REST endpoints to work immediately.

Project structure
src/main/java/org/example/endterm
│
├── controller     // REST controllers
├── service        // Business logic
├── repository     // JDBC database access
├── model          // Entities and DTOs
├── exception      // Custom exceptions
├── utils          // Database connection utils
└── EndtermApplication.java

JDBC & PreparedStatement

All database access is implemented using JDBC with PreparedStatement, not ORM.

Why:

protection from SQL Injection

explicit SQL control

clear understanding of database operations

matches course requirements

Each repository:

opens a connection

executes SQL queries

maps ResultSet to Java objects

safely closes resources

REST API (Spring Boot)

The project exposes REST endpoints using Spring Boot controllers.

Example endpoints:
Teams

GET /teams — get all teams


Players

GET /players

GET /players/top

GET /players/kills

Articles

GET /articles


Inventory

GET /inventory/player/{id}

GET /inventory/top

All endpoints were tested using Postman.

Business logic (Service layer)

Implemented logic includes:

Top teams by rating

Top players by rating

Kill/Death (K/D) calculation

Player inventory total value

Top inventories by market value

CS2 map listing and active pool logic

Articles system

Articles are implemented using inheritance and abstraction:

Article (abstract class)

CS2Article

EsportsArticle

Features:

abstract methods (getSource, getCategory)

polymorphism in repository

clean separation of article types

Error handling

Custom runtime exceptions were introduced to separate:

input validation errors

database errors

business logic errors

This improves code readability and debugging.

How to run the project

Create PostgreSQL database

Run schema.sql

Configure database credentials

Open project in IntelliJ IDEA

Run EndtermApplication

Test endpoints via browser or Postman

What I learned

Relational database design

JDBC and SQL integration

Spring Boot REST architecture

Layered backend structure

Working with real-world domain logic

Debugging backend errors

Using Postman for API testing

Final note

This project demonstrates:

strong understanding of databases

clean JDBC usage

RESTful backend development

real CS2 esports domain modeling
