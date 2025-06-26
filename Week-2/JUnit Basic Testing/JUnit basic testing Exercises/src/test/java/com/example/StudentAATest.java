package com.example;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures, Setup and Teardown Methods in JUnit
 * This class demonstrates the AAA pattern and proper use of @Before and @After annotations.
 */
public class StudentAATest {

    // Test fixtures - objects that are used in multiple tests
    private Student student;
    private Student graduateStudent;
    private Student freshmanStudent;

    /**
     * Setup method that runs before each test method.
     * This is where we ARRANGE our test fixtures and initial state.
     */
    @Before
    public void setUp() {
        System.out.println("Setting up test fixtures for StudentAATest...");
        
        // Arrange: Create test fixtures
        student = new Student("John Doe", 20, "Computer Science");
        graduateStudent = new Student("Jane Smith", 25, "Engineering");
        freshmanStudent = new Student("Bob Johnson", 18, "Mathematics");
        
        // Set up initial states
        student.enroll();
        graduateStudent.enroll();
        freshmanStudent.enroll();
        
        System.out.println("Test fixtures ready.");
    }

    /**
     * Cleanup method that runs after each test method.
     * This is where we clean up resources and reset state.
     */
    @After
    public void tearDown() {
        System.out.println("Cleaning up test fixtures...");
        
        // Clean up test fixtures
        student = null;
        graduateStudent = null;
        freshmanStudent = null;
        
        System.out.println("Test fixtures cleaned up.");
    }

    /**
     * Test using AAA pattern - Testing student enrollment.
     */
    @Test
    public void testStudentEnrollment() {
        // Arrange - Setup is done in @Before method
        String expectedName = "John Doe";
        int expectedAge = 20;
        
        // Act
        boolean isEnrolled = student.isEnrolled();
        String actualName = student.getName();
        int actualAge = student.getAge();
        
        // Assert
        assertTrue("Student should be enrolled", isEnrolled);
        assertEquals("Student name should match", expectedName, actualName);
        assertEquals("Student age should match", expectedAge, actualAge);
    }

    /**
     * Test using AAA pattern - Testing grade calculation.
     */
    @Test
    public void testGradeCalculation() {
        // Arrange
        double grade1 = 85.0;
        double grade2 = 90.0;
        double grade3 = 78.0;
        double expectedAverage = (grade1 + grade2 + grade3) / 3;
        
        // Act
        student.addGrade(grade1);
        student.addGrade(grade2);
        student.addGrade(grade3);
        double actualAverage = student.calculateAverageGrade();
        
        // Assert
        assertEquals("Average grade should be calculated correctly", 
                     expectedAverage, actualAverage, 0.001);
    }

    /**
     * Test using AAA pattern - Testing letter grade assignment.
     */
    @Test
    public void testLetterGradeAssignment() {
        // Arrange
        double gradeA = 95.0;
        double gradeB = 85.0;
        double gradeC = 75.0;
        double gradeD = 65.0;
        double gradeF = 45.0;
        
        // Act & Assert for Grade A
        graduateStudent.addGrade(gradeA);
        String letterGradeA = graduateStudent.getLetterGrade();
        assertEquals("Grade A should be assigned for 95%", "A", letterGradeA);
        
        // Arrange for next test (reset grades)
        Student testStudent = new Student("Test Student", 20);
        
        // Act & Assert for Grade B
        testStudent.addGrade(gradeB);
        String letterGradeB = testStudent.getLetterGrade();
        assertEquals("Grade B should be assigned for 85%", "B", letterGradeB);
        
        // Act & Assert for Grade C
        testStudent = new Student("Test Student", 20);
        testStudent.addGrade(gradeC);
        String letterGradeC = testStudent.getLetterGrade();
        assertEquals("Grade C should be assigned for 75%", "C", letterGradeC);
        
        // Act & Assert for Grade D
        testStudent = new Student("Test Student", 20);
        testStudent.addGrade(gradeD);
        String letterGradeD = testStudent.getLetterGrade();
        assertEquals("Grade D should be assigned for 65%", "D", letterGradeD);
        
        // Act & Assert for Grade F
        testStudent = new Student("Test Student", 20);
        testStudent.addGrade(gradeF);
        String letterGradeF = testStudent.getLetterGrade();
        assertEquals("Grade F should be assigned for 45%", "F", letterGradeF);
    }

    /**
     * Test using AAA pattern - Testing student withdrawal.
     */
    @Test
    public void testStudentWithdrawal() {
        // Arrange
        boolean initialEnrollmentStatus = student.isEnrolled();
        
        // Act
        student.withdraw();
        boolean finalEnrollmentStatus = student.isEnrolled();
        
        // Assert
        assertTrue("Student should initially be enrolled", initialEnrollmentStatus);
        assertFalse("Student should not be enrolled after withdrawal", finalEnrollmentStatus);
    }

    /**
     * Test using AAA pattern - Testing passing status.
     */
    @Test
    public void testPassingStatus() {
        // Arrange
        double passingGrade1 = 70.0;
        double passingGrade2 = 80.0;
        double failingGrade1 = 40.0;
        double failingGrade2 = 50.0;
        
        // Act - Test passing scenario
        student.addGrade(passingGrade1);
        student.addGrade(passingGrade2);
        boolean isPassingWithGoodGrades = student.isPassing();
        
        // Act - Test failing scenario
        freshmanStudent.addGrade(failingGrade1);
        freshmanStudent.addGrade(failingGrade2);
        boolean isPassingWithBadGrades = freshmanStudent.isPassing();
        
        // Assert
        assertTrue("Student with good grades should be passing", isPassingWithGoodGrades);
        assertFalse("Student with bad grades should not be passing", isPassingWithBadGrades);
    }

    /**
     * Test using AAA pattern - Testing credit calculation.
     */
    @Test
    public void testCreditCalculation() {
        // Arrange
        int numberOfGrades = 4;
        int creditsPerGrade = 3;
        int expectedTotalCredits = numberOfGrades * creditsPerGrade;
        
        // Act
        student.addGrade(85.0);
        student.addGrade(90.0);
        student.addGrade(78.0);
        student.addGrade(88.0);
        int actualTotalCredits = student.getTotalCredits();
        
        // Assert
        assertEquals("Total credits should be calculated correctly", 
                     expectedTotalCredits, actualTotalCredits);
    }

    /**
     * Test using AAA pattern - Testing exception handling for invalid age.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAgeException() {
        // Arrange
        int invalidAge = -5;
        
        // Act
        student.setAge(invalidAge);
        
        // Assert - Exception should be thrown (handled by expected annotation)
    }

    /**
     * Test using AAA pattern - Testing exception handling for invalid grade.
     */
    @Test
    public void testInvalidGradeException() {
        // Arrange
        double invalidGrade = 105.0;
        
        // Act & Assert
        try {
            student.addGrade(invalidGrade);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Grade must be between 0.0 and 100.0", e.getMessage());
        }
    }

    /**
     * Test using AAA pattern - Testing edge case with no grades.
     */
    @Test
    public void testNoGradesScenario() {
        // Arrange
        Student newStudent = new Student("Empty Student", 19);
        double expectedAverage = 0.0;
        String expectedLetterGrade = "F";
        int expectedCredits = 0;
        
        // Act
        double actualAverage = newStudent.calculateAverageGrade();
        String actualLetterGrade = newStudent.getLetterGrade();
        int actualCredits = newStudent.getTotalCredits();
        boolean isPassing = newStudent.isPassing();
        
        // Assert
        assertEquals("Average should be 0.0 with no grades", expectedAverage, actualAverage, 0.001);
        assertEquals("Letter grade should be F with no grades", expectedLetterGrade, actualLetterGrade);
        assertEquals("Credits should be 0 with no grades", expectedCredits, actualCredits);
        assertFalse("Student should not be passing with no grades", isPassing);
    }
} 