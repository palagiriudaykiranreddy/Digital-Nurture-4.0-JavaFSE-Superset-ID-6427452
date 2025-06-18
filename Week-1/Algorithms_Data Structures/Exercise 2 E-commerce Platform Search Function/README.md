# E-commerce Platform Search Implementation

This project demonstrates different search algorithms for an e-commerce platform, comparing their performance and use cases.

## Understanding Big O Notation

Big O notation is used to describe the performance or complexity of an algorithm:
- O(1): Constant time - Operations that take the same time regardless of input size
- O(log n): Logarithmic time - Operations that divide the problem in half each time (e.g., binary search)
- O(n): Linear time - Operations that grow linearly with input size (e.g., linear search)
- O(n log n): Linearithmic time - Operations like sorting with comparison-based algorithms

## Project Structure

- `Product.java`: Product class with attributes (id, name, category, price)
- `SearchAlgorithms.java`: Implementation of search algorithms
- `SearchDemo.java`: Demonstration and comparison of search algorithms

## Search Implementations

1. **Linear Search (O(n))**
   - Searches through elements one by one
   - Best case: O(1) - First element matches
   - Average case: O(n/2)
   - Worst case: O(n) - Last element or not found
   - Used for: name search, category search

2. **Binary Search (O(log n))**
   - Requires sorted data
   - Best case: O(1) - Middle element matches
   - Average/Worst case: O(log n)
   - Used for: ID search in sorted lists

## How to Run

1. Compile all Java files:
   ```
   javac *.java
   ```

2. Run the demo:
   ```
   java SearchDemo
   ```

## Analysis and Recommendations

1. **When to Use Linear Search:**
   - Unsorted data
   - Searching by non-unique fields (name, category)
   - Small datasets
   - When sorting overhead is significant

2. **When to Use Binary Search:**
   - Sorted data
   - Searching by unique identifier (ID)
   - Large datasets
   - Frequent search operations

3. **Production Considerations:**
   - Use database indexing for large datasets
   - Consider implementing caching
   - Use appropriate data structures (e.g., HashMaps for ID lookups)
   - Implement pagination for large result sets 