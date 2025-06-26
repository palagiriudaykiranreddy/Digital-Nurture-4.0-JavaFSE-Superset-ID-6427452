# JUnit Basic Testing Project

A comprehensive Java project demonstrating JUnit testing fundamentals, assertions, and the Arrange-Act-Assert (AAA) pattern.

## Project Structure

```
JUnit Basic Testing/
├── src/
│   ├── main/java/com/example/
│   │   ├── Calculator.java          # Basic calculator with arithmetic operations
│   │   └── Student.java             # Student management class with validation
│   └── test/java/com/example/
│       ├── CalculatorTest.java      # Tests for Calculator class
│       ├── AssertionsTest.java      # Demonstrates various JUnit assertions
│       └── StudentAATest.java       # AAA pattern and test fixtures demo
├── lib/
│   ├── junit-4.13.2.jar            # JUnit testing framework
│   └── hamcrest-core-1.3.jar       # Hamcrest assertion library
├── pom.xml                          # Maven configuration
├── README.md                        # This file
└── output.txt                       # Test execution results
```

## Requirements

- Java 8 or later
- JUnit 4.13.2 (included in lib/)
- Hamcrest Core 1.3 (included in lib/)

## Quick Start

### Compile the Code
```bash
# Compile main classes
javac -cp "lib/*" -d target/classes src/main/java/com/example/*.java

# Compile test classes
javac -cp "lib/*:target/classes" -d target/test-classes src/test/java/com/example/*.java
```

### Run Tests
```bash
# Run all tests
java -cp "lib/*:target/classes:target/test-classes" org.junit.runner.JUnitCore com.example.CalculatorTest com.example.AssertionsTest com.example.StudentAATest

# Run individual test classes
java -cp "lib/*:target/classes:target/test-classes" org.junit.runner.JUnitCore com.example.CalculatorTest
```

### Using Maven (Alternative)
```bash
mvn clean compile test
```

## Components Overview

### 1. Calculator Testing
**File**: `Calculator.java` & `CalculatorTest.java`
- Basic arithmetic operations (add, subtract, multiply, divide)
- Even number validation
- Factorial calculation
- Exception handling for edge cases
- 13 comprehensive test methods

### 2. JUnit Assertions Demo
**File**: `AssertionsTest.java`
- Basic assertions: `assertEquals`, `assertTrue`, `assertFalse`, `assertNull`, `assertNotNull`
- Advanced assertions: floating-point comparisons, array equality, object references
- Custom failure messages for better debugging
- Boundary testing and edge cases
- 5 test methods covering different assertion types

### 3. AAA Pattern & Test Fixtures
**Files**: `Student.java` & `StudentAATest.java`
- Demonstrates Arrange-Act-Assert pattern
- Test fixtures with `@Before` and `@After` methods
- Student class with grade management and validation
- Exception testing using multiple approaches
- 9 test methods showing proper test structure

## Key Features Demonstrated

### Testing Fundamentals
- JUnit annotations (`@Test`, `@Before`, `@After`)
- Test lifecycle management
- Assertion methods and custom messages
- Exception testing with `@Test(expected = Exception.class)`

### Best Practices
- AAA (Arrange-Act-Assert) pattern
- Test isolation and independence
- Meaningful test names and failure messages
- Edge case and boundary testing
- Input validation and error handling

### Advanced Concepts
- Test fixtures and setup/teardown methods
- Object state management and validation
- Floating-point comparisons with delta
- Array and object equality testing
- Multiple exception testing approaches

## Class Details

### Calculator
- **Operations**: add, subtract, multiply, divide, factorial
- **Validation**: even number checking, error handling
- **Edge Cases**: division by zero, negative factorial input

### Student
- **Attributes**: name, age, grades, major, enrollment status
- **Methods**: grade management, average calculation, letter grades
- **Validation**: age limits, grade bounds, name requirements
- **Business Logic**: passing status, credit calculation

## Test Coverage

- **Total Tests**: 27 test methods
- **Success Rate**: 100% (all tests pass)
- **Coverage Areas**: Basic operations, edge cases, exception handling, validation
- **Patterns**: Unit testing, integration concepts, AAA structure

## Learning Outcomes

This project demonstrates:
1. **JUnit Basics**: Setting up and writing unit tests
2. **Assertion Mastery**: Using appropriate assertions for different scenarios
3. **Test Organization**: Structuring tests with AAA pattern
4. **Error Testing**: Validating exception handling
5. **Best Practices**: Writing maintainable and reliable tests

## Output

All test execution results and detailed breakdowns are available in `output.txt`. 