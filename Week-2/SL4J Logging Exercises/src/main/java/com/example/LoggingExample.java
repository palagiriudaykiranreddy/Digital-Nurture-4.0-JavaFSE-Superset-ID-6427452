package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.info("Starting SLF4J Logging Example Application");
        
        demonstrateLoggingLevels();
        simulateApplicationWorkflow();
        
        logger.info("SLF4J Logging Example Application completed");
    }
    
    private static void demonstrateLoggingLevels() {
        logger.info("=== Demonstrating Different Logging Levels ===");
        
        logger.error("This is an error message - something went wrong!");
        logger.warn("This is a warning message - be careful!");
        logger.info("This is an info message - general information");
        logger.debug("This is a debug message - detailed debugging info");
        logger.trace("This is a trace message - very detailed tracing info");
    }
    
    private static void simulateApplicationWorkflow() {
        logger.info("=== Simulating Application Workflow ===");
        
        try {
            logger.info("Initializing application components...");
            logger.debug("Processing user data...");
            logger.warn("User session timeout is approaching");
            logger.info("Data processing completed successfully");
            
            simulateErrorCondition();
            
        } catch (Exception e) {
            logger.error("An unexpected error occurred during workflow execution", e);
        }
    }
    
    private static void simulateErrorCondition() {
        try {
            logger.debug("Attempting risky operation...");
            int result = 10 / 0;
            
        } catch (ArithmeticException e) {
            logger.error("Mathematical error occurred: Division by zero", e);
            logger.warn("Continuing execution with default values");
        }
    }
} 