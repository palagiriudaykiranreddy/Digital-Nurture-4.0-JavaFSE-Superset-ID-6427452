package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Student class for demonstrating AAA pattern and test fixtures.
 * This class represents a student with basic academic information.
 */
public class Student {
    private String name;
    private int age;
    private List<Double> grades;
    private String major;
    private boolean isEnrolled;

    /**
     * Default constructor.
     */
    public Student() {
        this.grades = new ArrayList<>();
        this.isEnrolled = false;
    }

    /**
     * Constructor with name and age.
     */
    public Student(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    /**
     * Constructor with all fields.
     */
    public Student(String name, int age, String major) {
        this(name, age);
        this.major = major;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150");
        }
        this.age = age;
    }

    public List<Double> getGrades() {
        return new ArrayList<>(grades); // Return a copy to maintain encapsulation
    }

    public void addGrade(double grade) {
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Grade must be between 0.0 and 100.0");
        }
        grades.add(grade);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void enroll() {
        this.isEnrolled = true;
    }

    public void withdraw() {
        this.isEnrolled = false;
    }

    /**
     * Calculate the average grade.
     * @return average grade or 0.0 if no grades
     */
    public double calculateAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    /**
     * Check if student is passing (average >= 60.0).
     * @return true if passing, false otherwise
     */
    public boolean isPassing() {
        return calculateAverageGrade() >= 60.0;
    }

    /**
     * Get the letter grade based on average.
     * @return letter grade (A, B, C, D, F)
     */
    public String getLetterGrade() {
        double average = calculateAverageGrade();
        
        if (average >= 90.0) return "A";
        if (average >= 80.0) return "B";
        if (average >= 70.0) return "C";
        if (average >= 60.0) return "D";
        return "F";
    }

    /**
     * Get the number of credits based on grades count.
     * Assumes each grade represents 3 credits.
     * @return total credits
     */
    public int getTotalCredits() {
        return grades.size() * 3;
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, major='%s', enrolled=%b, grades=%d, average=%.2f}",
                name, age, major, isEnrolled, grades.size(), calculateAverageGrade());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Student student = (Student) obj;
        return age == student.age &&
               isEnrolled == student.isEnrolled &&
               (name != null ? name.equals(student.name) : student.name == null) &&
               (major != null ? major.equals(student.major) : student.major == null) &&
               grades.equals(student.grades);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + grades.hashCode();
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (isEnrolled ? 1 : 0);
        return result;
    }
} 