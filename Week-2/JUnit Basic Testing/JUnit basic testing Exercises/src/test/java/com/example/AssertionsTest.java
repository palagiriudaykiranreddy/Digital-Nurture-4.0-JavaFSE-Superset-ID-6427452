package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 3: Assertions in JUnit
 * This class demonstrates various JUnit assertion methods and their usage.
 */
public class AssertionsTest {

    /**
     * Test method demonstrating basic JUnit assertions as specified in Exercise 3.
     */
    @Test
    public void testAssertions() {
        // Assert equals - checks if two values are equal
        assertEquals(5, 2 + 3);
        assertEquals("Hello", "Hel" + "lo");
        assertEquals(2.5, 5.0 / 2.0, 0.001); // With delta for double comparison

        // Assert true - checks if condition is true
        assertTrue(5 > 3);
        assertTrue("JUnit".length() == 5);

        // Assert false - checks if condition is false
        assertFalse(5 < 3);
        assertFalse("".equals("Hello"));

        // Assert null - checks if object is null
        String nullString = null;
        assertNull(nullString);

        // Assert not null - checks if object is not null
        assertNotNull(new Object());
        assertNotNull("This is not null");
    }

    /**
     * Additional assertion examples for comprehensive demonstration.
     */
    @Test
    public void testAdvancedAssertions() {
        // Array assertions
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals("Arrays should be equal", expected, actual);

        // String assertions with custom messages
        assertEquals("String equality test failed", "JUnit", "JUnit");
        
        // Assert same - checks if two references point to the same object
        String str1 = "Hello";
        String str2 = str1;
        assertSame("Should be the same object", str1, str2);

        // Assert not same - checks if two references point to different objects
        String str3 = new String("Hello");
        String str4 = new String("Hello");
        assertNotSame("Should be different objects", str3, str4);
    }

    /**
     * Testing assertions with custom error messages.
     */
    @Test
    public void testAssertionsWithMessages() {
        // All assertions can include custom messages for better error reporting
        assertEquals("Addition should work correctly", 10, 5 + 5);
        assertTrue("Number should be positive", 42 > 0);
        assertFalse("Empty string should not equal non-empty string", "".equals("test"));
        assertNotNull("Object should not be null", new StringBuilder());
    }

    /**
     * Testing edge cases and boundary conditions with assertions.
     */
    @Test
    public void testBoundaryAssertions() {
        // Testing with boundary values
        assertEquals("Max integer test", Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals("Min integer test", Integer.MIN_VALUE, Integer.MIN_VALUE);
        
        // Testing with zero
        assertEquals("Zero equals zero", 0, 0);
        assertTrue("Zero is not positive", 0 >= 0);
        assertFalse("Zero is not negative", 0 < 0);
        
        // Testing with floating point precision
        double result = 1.0 / 3.0 * 3.0;
        assertEquals("Floating point precision test", 1.0, result, 0.0001);
    }

    /**
     * Testing collections and complex objects.
     */
    @Test
    public void testComplexObjectAssertions() {
        // Testing string operations
        String original = "JUnit Testing";
        String uppercase = original.toUpperCase();
        assertEquals("Uppercase conversion", "JUNIT TESTING", uppercase);
        
        // Testing length
        assertEquals("String length", 13, original.length());
        
        // Testing contains
        assertTrue("String should contain 'Unit'", original.contains("Unit"));
        assertFalse("String should not contain 'Python'", original.contains("Python"));
    }
} 