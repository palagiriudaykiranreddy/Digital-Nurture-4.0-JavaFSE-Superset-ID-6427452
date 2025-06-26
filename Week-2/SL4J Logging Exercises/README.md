

This project demonstrates logging error messages and warning levels using SLF4J (Simple Logging Facade for Java) with Logback as the logging implementation.

## Project Structure

```
├── pom.xml                           # Maven project configuration
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       └── LoggingExample.java   # Main application class
│       └── resources/
│           └── logback.xml           # Logback configuration
├── logs/
│   └── application.log               # Log file output
├── output.txt                        # Expected application output
└── README.md                         # This file
```

## Dependencies

The project uses the following dependencies:

- **SLF4J API (1.7.30)**: The logging facade
- **Logback Classic (1.2.3)**: The logging implementation

## Features Demonstrated

### Logging Levels
The application demonstrates all major SLF4J logging levels:

1. **ERROR**: Critical error messages
2. **WARN**: Warning messages for potentially harmful situations
3. **INFO**: Informational messages about application progress
4. **DEBUG**: Detailed information useful for debugging
5. **TRACE**: Very detailed information for troubleshooting

### Logging Configuration
The `logback.xml` configuration file sets up:

- **Console Appender**: Outputs logs to the console with time format `HH:mm:ss.SSS`
- **File Appender**: Outputs logs to `logs/application.log` with full timestamp
- **Log Level**: Set to DEBUG to show all log levels except TRACE
- **Pattern**: Includes timestamp, thread, log level, logger name, and message

## How to Run

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Build and Run
```bash
# Compile the project
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="com.example.LoggingExample"
```

### Alternative (without Maven)
If Maven is not available, you can:
1. Download the SLF4J and Logback JAR files manually
2. Compile and run with `javac` and `java` commands
3. Check the `output.txt` file for expected output

## Expected Output

The application will:
1. Display various logging levels in the console
2. Create a log file in the `logs/` directory
3. Demonstrate error handling with exception logging
4. Show the difference between console and file log formats

See `output.txt` for the complete expected output.

## Learning Objectives

After running this project, you will understand:

- How to set up SLF4J with Logback
- Different logging levels and when to use them
- How to configure logging output formats
- How to log exceptions with stack traces
- Best practices for application logging

## Key Code Examples

### Basic Logger Setup
```java
private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);
```

### Logging Different Levels
```java
logger.error("This is an error message");
logger.warn("This is a warning message");
logger.info("This is an info message");
logger.debug("This is a debug message");
```

### Exception Logging
```java
try {
    // risky operation
} catch (Exception e) {
    logger.error("Error occurred", e);
}
```