# Singleton Pattern Example

This project demonstrates the implementation of the Singleton design pattern using a Logger class.

## Project Structure
- `Logger.java`: Contains the Logger class implementing the Singleton pattern
- `LoggerTest.java`: Contains the test class to verify the Singleton implementation

## How to Run
1. Compile the Java files:
   ```
   javac Logger.java LoggerTest.java
   ```

2. Run the test class:
   ```
   java LoggerTest
   ```

## Expected Output
When you run the program, you should see:
1. A message indicating the Logger instance was created (only once)
2. Two log messages
3. A success message confirming both logger references point to the same instance
4. The same hash code for both logger instances

## Implementation Details
- The Logger class uses a private constructor to prevent direct instantiation
- The `getInstance()` method is synchronized to ensure thread safety
- Only one instance of the Logger class is created and reused throughout the application 