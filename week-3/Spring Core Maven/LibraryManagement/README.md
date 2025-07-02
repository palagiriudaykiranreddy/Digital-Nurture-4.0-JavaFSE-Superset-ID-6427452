# Library Management Application

A simple Spring Framework application that demonstrates how to use Inversion of Control (IoC) and Dependency Injection (DI) for managing library backend operations.

## What This Project Does

This project shows how to build a basic library management system using Spring Framework. It demonstrates three main concepts:

1. **Setting up a Spring project** - Creating a Maven project with Spring dependencies
2. **Using dependency injection** - Letting Spring manage how different parts of your application work together
3. **Configuring Maven** - Setting up the build tool to handle dependencies and compilation

## Project Structure

The project is organized in a standard Maven layout:

```
LibraryManagement/
├── pom.xml                                    # Maven project configuration
├── README.md                                  # This documentation
└── src/
    └── main/
        ├── java/com/library/
        │   ├── LibraryManagementApplication.java    # Main application
        │   ├── service/BookService.java             # Business logic
        │   └── repository/BookRepository.java       # Data handling
        └── resources/applicationContext.xml          # Spring configuration
```

## Key Components Explained

### 1. Maven Configuration (pom.xml)
This file tells Maven what your project needs:
- Project name: LibraryManagement
- Java version: 1.8
- Spring libraries: Core, Context, AOP, and WebMVC
- JUnit for testing

### 2. Spring Configuration (applicationContext.xml)
This XML file tells Spring how to create and connect your application components:
- Creates a BookRepository object
- Creates a BookService object
- Connects the BookRepository to the BookService automatically

### 3. Repository Layer (BookRepository.java)
This class handles data operations:
- Has a method called "save" that pretends to save books to a database
- Currently just prints a message to show it's working

### 4. Service Layer (BookService.java)
This class contains the business logic:
- Uses the BookRepository to save books
- Gets the BookRepository through dependency injection (Spring provides it automatically)
- Has a setter method that Spring uses to inject the repository

### 5. Main Application (LibraryManagementApplication.java)
This is the starting point of your application:
- Loads the Spring configuration
- Gets the BookService from Spring
- Calls the service to save a book and test everything works

## What Each Exercise Accomplished

### Exercise 1: Basic Spring Setup 
- Created a Maven project called LibraryManagement
- Added Spring Core dependencies to pom.xml
- Created the Spring configuration file (applicationContext.xml)
- Defined how Spring should create BookService and BookRepository objects
- Organized code into proper packages (service and repository)
- Created a main class to test the setup

### Exercise 2: Dependency Injection 
- Configured Spring to automatically provide BookRepository to BookService
- Added a setter method in BookService so Spring can inject the repository
- Tested that the dependency injection works by running the application
- Demonstrated how Spring manages object relationships

### Exercise 4: Maven Project Setup 
- Created a proper Maven project structure
- Added all required Spring dependencies (Context, AOP, WebMVC)
- Configured Maven to use Java 1.8
- Set up the standard Maven directory layout

## How to Build and Run

### What You Need
- Java 1.8 or newer installed on your computer
- Apache Maven 3.6 or newer

### Building the Project
1. Open a terminal and go to the LibraryManagement folder
2. Run this command to download dependencies and compile: `mvn clean compile`
3. Run this command to build everything: `mvn install`

### Running the Application
Use this command to run the main application:
```bash
mvn exec:java -Dexec.mainClass="com.library.LibraryManagementApplication"
```

You can also run it with Java directly after building:
```bash
java -cp target/classes:target/dependency/* com.library.LibraryManagementApplication
```

## What You Should See

When the application runs successfully, it will print:
```
Saving book: The Great Gatsby
```

This simple output proves that:
1. Spring loaded all the configuration correctly
2. Spring created the BookService and BookRepository objects
3. Spring successfully injected the BookRepository into the BookService
4. The BookService was able to use the BookRepository to "save" a book

## Key Spring Concepts Demonstrated

### Inversion of Control (IoC)
Instead of your code creating objects manually, Spring creates and manages them for you. You just tell Spring what you need, and it provides it.

### Dependency Injection (DI)
Spring automatically provides the BookRepository to the BookService. The service doesn't need to know how to create a repository - Spring handles that.

### XML Configuration
All the setup is done in a separate XML file, so you can change how things work without modifying your Java code.

## Benefits of This Approach

1. **Easier Testing**: You can easily replace real components with test versions
2. **Flexible Configuration**: Change how things work by editing XML, not code
3. **Loose Coupling**: Components don't need to know about each other's implementation details
4. **Better Organization**: Clear separation between different layers of your application
5. **Easier Maintenance**: Changes to one component don't break others

## Common Problems and Solutions

### If You Get Errors
1. **Can't find classes**: Make sure Maven downloaded all dependencies with `mvn install`
2. **Spring can't create beans**: Check that your XML configuration syntax is correct
3. **Can't find beans**: Verify that the bean names in XML match what you're requesting in Java

### Quick Checks
1. Make sure pom.xml has all the Spring dependencies
2. Verify applicationContext.xml is in the src/main/resources folder
3. Check that your Java packages match the class declarations
4. Ensure your BookService has the setter method for dependency injection
