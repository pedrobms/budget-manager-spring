# Budget Manager API

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

- POST `auth/login` - Login with email and password
- POST `auth/register` - Register a new user
- POST `auth/logout` - Logout


#### User:

- GET `users` - List all users (Admin only)
- POST `users` - Create a new user
- GET `users/{id}` - Get a user by id
- PUT `users/{id}` - Update a user by id
- DELETE `users/{id}` - Delete a user by id

#### Category:

- GET `users/{userId}/categories` - List all categories of a user
- GET `users/{userId}/categories/{type}` - List all categories of a user by type of transaction
- POST `users/{userId}/categories` - Create a new category for a user
- GET `users/{userId}/categories/{id}` - Get a category by id
- PUT `users/{userId}/categories/{id}` - Update a category by id
- DELETE `users/{userId}/categories/{id}` - Delete a category by id

#### Transaction:

- GET `users/{userId}/transactions` - List all transactions of a user
- GET `users/{userId}/transactions/type/{type}` - List all transactions of a user by type of transaction
- GET `users/{userId}/transactions/category/{categoryId}` - List all transactions of a user by category
- POST `users/{userId}/transactions` - Create a new transaction for a user
- GET `users/{userId}/transactions/{id}` - Get a transaction by id
- PUT `users/{userId}/transactions/{id}` - Update a transaction by id
- DELETE `users/{userId}/transactions/{id}` - Delete a transaction by id

#### Balance:

- GET `users/{userId}/balance` - Get the account balance of a user