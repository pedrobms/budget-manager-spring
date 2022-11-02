# Budget Manager

Budget Manager is a simple web application that allows you to manage your budget. It is written in Java using Spring Boot.

Developed as a project for the discipline of "*Programação Orientada a Objetos para web II* (Object Oriented Programming for web II)" at the "*Universidade Federal de Santa Maria* (Federal University of Santa Maria)".

### General information
---
This rest API allows:
- All CRUD operations for the following entities:
    - User
    - Category
    - Transaction
- Authentication and authorization using JWT
- Getting the current user's balance

#### Technologies:
- Spring Boot 2.7.5

#### Dependencies:
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring DevTools
- H2 Database


### Endpoints:
---
#### Authentication:

- POST `/login` - Login with email and password
- POST `/register` - Register a new user


#### User:

- GET `users` - List all users (Admin only)
- POST `users` - Create a new user
- GET `users/{id}` - Get a user by id
- PUT `users/{id}` - Update a user by id
- DELETE `users/{id}` - Delete a user by id

### Category:

- GET `user/{userId}/categories` - List all categories of a user
- GET `user/{userId}/categories/{type}` - List all categories of a user by type of transaction
- POST `user/{userId}/categories` - Create a new category for a user
- GET `user/{userId}/categories/{id}` - Get a category by id
- PUT `user/{userId}/categories/{id}` - Update a category by id
- DELETE `user/{userId}/categories/{id}` - Delete a category by id

### Transaction:

- GET `user/{userId}/transactions` - List all transactions of a user
- GET `user/{userId}/transactions/type/{type}` - List all transactions of a user by type of transaction
- GET `user/{userId}/transactions/category/{categoryId}` - List all transactions of a user by category
- POST `user/{userId}/transactions` - Create a new transaction for a user
- GET `user/{userId}/transactions/{id}` - Get a transaction by id
- PUT `user/{userId}/transactions/{id}` - Update a transaction by id
- DELETE `user/{userId}/transactions/{id}` - Delete a transaction by id

### Balance:

- GET `user/{userId}/balance` - Get the account balance of a user