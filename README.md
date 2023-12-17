# Sales Application

## Overview
Sales Application is a Spring Boot-based application designed to manage sales items and generate receipts. It provides a RESTful API for adding items, retrieving all items, and calculating taxes and receipts.

## Technology Stack
- **Spring Boot**: Framework for creating stand-alone, production-grade Spring-based applications.
- **H2 Database**: In-memory database used for storing item data.
- **MapStruct**: Mapping tool used for converting between entities and DTOs.
- **Lombok**: Java library used to reduce boilerplate code.
- **JUnit and Mockito**: Used for unit testing.

## Setup and Installation
1. **Prerequisites**:
   - Java 8 or lastest
   - Maven

2. **Running the Application**:
   - Clone the repository: `git clone [repository-url]`
   - Navigate to the project directory: `cd sales`
   - Build the project: `mvn clean install`
   - Run the application: `mvn spring-boot:run`

## Usage Instructions
- **Add Item**:
  - **Endpoint**: `POST /items`
  - **Body**: `{"name": "item name", "price": price, "quantity": quantity, "isImported": boolean, "isExempt": boolean}`
- **Get All Items**:
  - **Endpoint**: `GET /items`
- **Generate Receipt**:
  - **Endpoint**: `GET /items/receipt`


## Architecture Overview
The application follows a layered architecture with the following layers:
- **Controller Layer**: REST controllers handling HTTP requests.
- **Service Layer**: Business logic implementation.
- **Repository Layer**: Data access logic using Spring Data JPA.
- **DTOs and Mappers**: Used for transferring data between layers and mapping between entities and DTOs.

## Error Handling
Custom exceptions and global error handling are implemented to provide meaningful error responses to API clients.

## Running Tests
Execute unit tests using the command: `mvn test`

