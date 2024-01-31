## Wise-wallet backend
An expense management app, that can track your wallet health,
expenses and incomes. This project contains a Rest API created 
with Spring Boot 3 and Java 21, following event driven 
architecture with microservices.

The main purpose of this project is to study the various
technologies involving microservices and event driven arch.
During the development, i learned many features about Java
and Spring framework, like Java reflection, dependency injection
with spring, swagger documentation, maven multi-modules and more.

### Content
- [1. Key features](#1-key-features)
- [2. Technical Guide](#2-technical-guide)
  - [2.1 Clean Architecture](#21-clean-architecture)
  - [2.2 Event Driven Architecture](#22-event-driven-architecture)
  - [2.3 Microservices](#23-microservices)
  - [2.4 Command and Query Responsibility Segregation (CQRS)](#24-command-and-query-responsibility-segregation-cqrs)
- [2. Class diagram](#2-class-diagram)
- [3. Architecture](#3-system-architecture)

### 1. Key features
- Microservices and event driven architecture;
- Command and Query Responsibility Segregation (CQRS);
- MongoDB as read database;
- PostgreSQL as Event Store;
- Apache Kafka as microservice communication;
- Domain Driven Design (DDD) concepts and Clean Architecture.

### 2. Technical guide
### 2.1 Clean Architecture
![Clean Architecture](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg "Clean Architecture")

Clean Architecture is a software architecture proposed by Robert C. Martin.
The main purpose of this architecture is to protect the business logic
of the software from external dependencies like database, frontend UI,
frameworks and libraries. The business logic must be decoupled from external dependencies, and it
can bring several benefits.

### 2.2 Event Driven Architecture

### 2.3 Microservices
### 2.4 Command and Query Responsibility Segregation (CQRS)


### 2. Class diagram

![Class Diagram](./docs/class_diagram.png?raw=true "Class Diagram")

### 3. System architecture

![System architecture](./docs/arch.png?raw=true "System architecture")