package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

/**
 * JUnit test class for Calculator.
 * This class demonstrates various JUnit testing features and best practices.
 */
public class CalculatorTest {

    private Calculator calculator;

    /**
     * Setup method that runs before each test method.
     * This is where we initialize objects needed for testing.
     */
    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Setting up test environment...");
    }

    /**
     * Cleanup method that runs after each test method.
     */
    @After
    public void tearDown() {
        calculator = null;
        System.out.println("Cleaning up after test...");
    }

    /**
     * Test the add method with positive numbers.
     */
    @Test
    public void testAddPositiveNumbers() {
        // Arrange
        int a = 5;
        int b = 3;
        int expected = 8;

        // Act
        int actual = calculator.add(a, b);

        // Assert
        assertEquals("Addition of positive numbers should work correctly", expected, actual);
    }

    /**
     * Test the add method with negative numbers.
     */
    @Test
    public void testAddNegativeNumbers() {
        assertEquals(-8, calculator.add(-5, -3));
        assertEquals(2, calculator.add(5, -3));
        assertEquals(-2, calculator.add(-5, 3));
    }

    /**
     * Test the add method with zero.
     */
    @Test
    public void testAddWithZero() {
        assertEquals(5, calculator.add(5, 0));
        assertEquals(5, calculator.add(0, 5));
        assertEquals(0, calculator.add(0, 0));
    }

    /**
     * Test the subtract method.
     */
    @Test
    public void testSubtract() {
        assertEquals(2, calculator.subtract(5, 3));
        assertEquals(-2, calculator.subtract(3, 5));
        assertEquals(0, calculator.subtract(5, 5));
    }

    /**
     * Test the multiply method.
     */
    @Test
    public void testMultiply() {
        assertEquals(15, calculator.multiply(3, 5));
        assertEquals(-15, calculator.multiply(-3, 5));
        assertEquals(15, calculator.multiply(-3, -5));
        assertEquals(0, calculator.multiply(3, 0));
    }

    /**
     * Test the divide method with valid inputs.
     */
    @Test
    public void testDivide() {
        assertEquals(2.0, calculator.divide(6, 3), 0.001);
        assertEquals(2.5, calculator.divide(5, 2), 0.001);
        assertEquals(-2.0, calculator.divide(-6, 3), 0.001);
    }

    /**
     * Test the divide method with division by zero.
     * This should throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calculator.divide(5, 0);
    }

    /**
     * Alternative way to test exception using try-catch.
     */
    @Test
    public void testDivideByZeroWithTryCatch() {
        try {
            calculator.divide(10, 0);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Division by zero is not allowed", e.getMessage());
        }
    }

    /**
     * Test the isEven method.
     */
    @Test
    public void testIsEven() {
        assertTrue("2 should be even", calculator.isEven(2));
        assertTrue("0 should be even", calculator.isEven(0));
        assertTrue("-4 should be even", calculator.isEven(-4));
        
        assertFalse("1 should be odd", calculator.isEven(1));
        assertFalse("3 should be odd", calculator.isEven(3));
        assertFalse("-5 should be odd", calculator.isEven(-5));
    }

    /**
     * Test the factorial method with valid inputs.
     */
    @Test
    public void testFactorial() {
        assertEquals(1, calculator.factorial(0));
        assertEquals(1, calculator.factorial(1));
        assertEquals(2, calculator.factorial(2));
        assertEquals(6, calculator.factorial(3));
        assertEquals(24, calculator.factorial(4));
        assertEquals(120, calculator.factorial(5));
    }

    /**
     * Test the factorial method with negative input.
     * This should throw an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegativeNumber() {
        calculator.factorial(-1);
    }

    /**
     * Test multiple operations together.
     */
    @Test
    public void testComplexOperations() {
        // Test: (5 + 3) * 2 - 4 = 12
        int step1 = calculator.add(5, 3);        // 8
        int step2 = calculator.multiply(step1, 2); // 16
        int result = calculator.subtract(step2, 4); // 12
        
        assertEquals(12, result);
    }

    /**
     * Test with boundary values.
     */
    @Test
    public void testBoundaryValues() {
        // Test with maximum integer values
        assertEquals(Integer.MAX_VALUE, calculator.add(Integer.MAX_VALUE, 0));
        assertEquals(Integer.MIN_VALUE, calculator.add(Integer.MIN_VALUE, 0));
        
        // Test multiplication with 1
        assertEquals(42, calculator.multiply(42, 1));
        assertEquals(0, calculator.multiply(42, 0));
    }
} 