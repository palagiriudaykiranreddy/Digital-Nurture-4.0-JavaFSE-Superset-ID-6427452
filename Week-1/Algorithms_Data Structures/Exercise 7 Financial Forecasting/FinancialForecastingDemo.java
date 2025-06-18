public class FinancialForecastingDemo {
    public static void main(String[] args) {
        // Test parameters
        double principal = 10000.0;  // Initial investment
        double rate = 0.08;          // 8% annual interest rate
        int years = 10;              // Investment period
        
        System.out.println("Financial Forecasting Demonstration");
        System.out.println("=================================");
        System.out.println("Initial Investment: $" + String.format("%.2f", principal));
        System.out.println("Annual Interest Rate: " + (rate * 100) + "%");
        System.out.println("Investment Period: " + years + " years");
        System.out.println();
        
        // Test different future value calculation methods
        System.out.println("Future Value Calculations:");
        System.out.println("------------------------");
        
        // Simple recursive calculation
        long startTime = System.nanoTime();
        double recursiveResult = FinancialForecasting.calculateFutureValueRecursive(principal, rate, years);
        long recursiveTime = System.nanoTime() - startTime;
        System.out.println("Recursive Method Result: $" + String.format("%.2f", recursiveResult));
        System.out.println("Recursive Method Time: " + recursiveTime + " ns");
        System.out.println();
        
        // Memoized recursive calculation
        startTime = System.nanoTime();
        double memoizedResult = FinancialForecasting.calculateFutureValueMemoized(principal, rate, years);
        long memoizedTime = System.nanoTime() - startTime;
        System.out.println("Memoized Method Result: $" + String.format("%.2f", memoizedResult));
        System.out.println("Memoized Method Time: " + memoizedTime + " ns");
        System.out.println();
        
        // Iterative calculation
        startTime = System.nanoTime();
        double iterativeResult = FinancialForecasting.calculateFutureValueIterative(principal, rate, years);
        long iterativeTime = System.nanoTime() - startTime;
        System.out.println("Iterative Method Result: $" + String.format("%.2f", iterativeResult));
        System.out.println("Iterative Method Time: " + iterativeTime + " ns");
        System.out.println();
        
        // Test prediction based on historical growth rates
        System.out.println("Growth Rate Based Prediction:");
        System.out.println("---------------------------");
        double currentValue = 10000.0;
        double[] historicalRates = {0.07, 0.08, 0.06, 0.09, 0.07}; // Historical growth rates
        int futurePeriods = 5;
        
        double predictedValue = FinancialForecasting.predictFutureValue(currentValue, historicalRates, futurePeriods);
        System.out.println("Current Value: $" + String.format("%.2f", currentValue));
        System.out.println("Predicted Value after " + futurePeriods + " periods: $" + String.format("%.2f", predictedValue));
        
        // Calculate and display CAGR
        double beginValue = currentValue;
        double endValue = predictedValue;
        double cagr = FinancialForecasting.calculateCAGR(beginValue, endValue, futurePeriods);
        System.out.println("Compound Annual Growth Rate (CAGR): " + String.format("%.2f%%", cagr * 100));
        
        // Performance Analysis
        System.out.println("\nPerformance Analysis:");
        System.out.println("-------------------");
        System.out.println("1. Simple Recursive Method:");
        System.out.println("   - Time Complexity: O(n)");
        System.out.println("   - Space Complexity: O(n) due to call stack");
        System.out.println("   - Pros: Simple to understand and implement");
        System.out.println("   - Cons: Can be inefficient for large n");
        
        System.out.println("\n2. Memoized Recursive Method:");
        System.out.println("   - Time Complexity: O(n) with caching");
        System.out.println("   - Space Complexity: O(n) for cache");
        System.out.println("   - Pros: Faster for repeated calculations");
        System.out.println("   - Cons: Uses additional memory for cache");
        
        System.out.println("\n3. Iterative Method:");
        System.out.println("   - Time Complexity: O(n)");
        System.out.println("   - Space Complexity: O(1)");
        System.out.println("   - Pros: Most efficient, no recursion overhead");
        System.out.println("   - Cons: May be less intuitive for complex calculations");
    }
} 