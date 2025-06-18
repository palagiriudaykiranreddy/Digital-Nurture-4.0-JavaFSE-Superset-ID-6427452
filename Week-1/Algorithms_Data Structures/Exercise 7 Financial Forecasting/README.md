# Financial Forecasting Tool

This project implements a financial forecasting tool using recursive algorithms and demonstrates various optimization techniques.

## Understanding Recursion

Recursion is a programming concept where a function calls itself to solve a problem by breaking it down into smaller subproblems. In financial forecasting, recursion can be particularly useful for:
- Calculating compound interest
- Predicting future values based on growth patterns
- Computing complex financial metrics

## Project Components

1. **FinancialForecasting.java**
   - Core implementation of financial calculations
   - Multiple approaches to future value calculation:
     - Simple recursive method
     - Memoized recursive method
     - Iterative method
   - Growth rate prediction
   - CAGR calculation

2. **FinancialForecastingDemo.java**
   - Demonstration of all implemented methods
   - Performance comparison
   - Example usage scenarios

## Implementation Details

### 1. Future Value Calculation Methods

a) **Simple Recursive Method**
   ```java
   FV(n) = FV(n-1) * (1 + r)
   ```
   - Time Complexity: O(n)
   - Space Complexity: O(n)

b) **Memoized Recursive Method**
   - Uses caching to store intermediate results
   - Time Complexity: O(n)
   - Space Complexity: O(n)
   - Faster for repeated calculations

c) **Iterative Method**
   - Most efficient implementation
   - Time Complexity: O(n)
   - Space Complexity: O(1)

### 2. Optimization Techniques

1. **Memoization**
   - Caches previously calculated values
   - Prevents redundant calculations
   - Trades memory for speed

2. **Base Case Optimization**
   - Handles edge cases efficiently
   - Prevents unnecessary recursion

## How to Run

1. Compile the Java files:
   ```
   javac *.java
   ```

2. Run the demo:
   ```
   java FinancialForecastingDemo
   ```

## Analysis and Recommendations

1. **When to Use Recursive Solution:**
   - When the problem naturally breaks down into similar subproblems
   - When code clarity is more important than performance
   - For relatively small input sizes

2. **When to Use Memoized Solution:**
   - When calculations are frequently repeated
   - When trading memory for speed is acceptable
   - For medium to large input sizes with repeated calculations

3. **When to Use Iterative Solution:**
   - When maximum performance is required
   - When memory usage needs to be minimized
   - For large input sizes

## Performance Considerations

- Recursive solutions may cause stack overflow for very large n
- Memoization significantly improves performance for repeated calculations
- Iterative solution is most efficient but may be less intuitive
- Consider using BigDecimal for high-precision financial calculations in production 