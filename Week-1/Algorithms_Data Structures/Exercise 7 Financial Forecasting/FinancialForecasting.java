import java.util.HashMap;
import java.util.Map;

public class FinancialForecasting {
    // Cache for memoization
    private static Map<String, Double> memoizationCache = new HashMap<>();
    
    /**
     * Simple recursive future value calculation
     * Time Complexity: O(n) where n is the number of years
     * Space Complexity: O(n) due to recursive call stack
     */
    public static double calculateFutureValueRecursive(double principal, double rate, int years) {
        // Base case
        if (years == 0) {
            return principal;
        }
        
        // Recursive case: FV = PV * (1 + r)^n
        return calculateFutureValueRecursive(principal, rate, years - 1) * (1 + rate);
    }
    
    /**
     * Optimized recursive future value calculation using memoization
     * Time Complexity: O(n) but with caching
     * Space Complexity: O(n) for cache storage
     */
    public static double calculateFutureValueMemoized(double principal, double rate, int years) {
        // Create a unique key for this combination of parameters
        String key = String.format("%.2f-%.2f-%d", principal, rate, years);
        
        // Check if result is already in cache
        if (memoizationCache.containsKey(key)) {
            return memoizationCache.get(key);
        }
        
        // Base case
        if (years == 0) {
            return principal;
        }
        
        // Recursive case with memoization
        double result = calculateFutureValueMemoized(principal, rate, years - 1) * (1 + rate);
        memoizationCache.put(key, result);
        return result;
    }
    
    /**
     * Iterative future value calculation (most efficient)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static double calculateFutureValueIterative(double principal, double rate, int years) {
        double result = principal;
        for (int i = 0; i < years; i++) {
            result *= (1 + rate);
        }
        return result;
    }
    
    /**
     * Recursive method to predict future values based on historical growth rates
     * Time Complexity: O(n) where n is the number of future periods
     * Space Complexity: O(n) due to recursive call stack
     */
    public static double predictFutureValue(double currentValue, double[] historicalGrowthRates, int futurePeriods) {
        // Base case
        if (futurePeriods == 0) {
            return currentValue;
        }
        
        // Calculate average growth rate from historical data
        double avgGrowthRate = calculateAverageGrowthRate(historicalGrowthRates);
        
        // Recursive case
        return predictFutureValue(currentValue * (1 + avgGrowthRate), historicalGrowthRates, futurePeriods - 1);
    }
    
    /**
     * Helper method to calculate average growth rate
     */
    private static double calculateAverageGrowthRate(double[] rates) {
        if (rates == null || rates.length == 0) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (double rate : rates) {
            sum += rate;
        }
        return sum / rates.length;
    }
    
    /**
     * Method to calculate compound annual growth rate (CAGR)
     */
    public static double calculateCAGR(double beginningValue, double endingValue, int numberOfYears) {
        return Math.pow(endingValue / beginningValue, 1.0 / numberOfYears) - 1;
    }
} 