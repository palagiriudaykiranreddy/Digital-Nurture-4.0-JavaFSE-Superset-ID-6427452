

This project showcases essential Mockito testing patterns:
- **Mocking External Dependencies** - Creating mock objects for external APIs
- **Stubbing Methods** - Defining predictable return values for mocked methods  
- **Verifying Interactions** - Ensuring methods are called with correct parameters
- **Edge Case Testing** - Handling null inputs and error conditions

## 🏗️ Project Structure

```
src/
├── main/java/com/example/
│   ├── UserApi.java          # External API interface
│   └── UserService.java      # Service with business logic
└── test/java/com/example/
    └── UserServiceTest.java   # Comprehensive test suite
```

## 📚 Key Components

### UserApi.java
Interface representing an external API for user operations. In real applications, this would be implemented as a REST client or database access layer.

### UserService.java  
Service class containing business logic that depends on the UserApi. Includes input validation and error handling.

### UserServiceTest.java
Comprehensive test suite demonstrating 10 different Mockito concepts:
- Basic mocking and stubbing
- Parameter-specific stubbing
- Interaction verification
- Call count verification
- Exception handling tests

## 🎯 Learning Objectives

After studying this code, you'll understand:
- How to isolate units under test using mocks
- How to stub method returns for predictable testing
- How to verify correct interactions between components
- Best practices for testing error conditions
- Professional test organization and naming conventions

## 🔧 Dependencies

- **JUnit 5.9.2** - Testing framework
- **Mockito 5.1.1** - Mocking framework
- **Java 11+** - Runtime requirement
