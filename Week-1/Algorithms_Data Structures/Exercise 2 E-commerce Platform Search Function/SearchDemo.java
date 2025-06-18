import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SearchDemo {
    public static void main(String[] args) {
        // Create a list of sample products
        List<Product> products = generateSampleProducts(10000); // Generate 10,000 products
        
        // Sort products by ID for binary search
        Collections.sort(products);
        
        System.out.println("E-commerce Platform Search Algorithm Comparison");
        System.out.println("=============================================");
        
        // Test searching by ID
        int targetId = 5000; // Search for product in the middle
        System.out.println("\nSearching for product with ID: " + targetId);
        
        // Linear Search
        System.out.println("\nLinear Search:");
        Product linearResult = SearchAlgorithms.linearSearchById(products, targetId);
        if (linearResult != null) {
            System.out.println("Found: " + linearResult);
        }
        
        // Binary Search
        System.out.println("\nBinary Search:");
        Product binaryResult = SearchAlgorithms.binarySearchById(products, targetId);
        if (binaryResult != null) {
            System.out.println("Found: " + binaryResult);
        }
        
        // Test searching by name
        System.out.println("\nSearching for products with name containing 'Product 500':");
        List<Product> nameResults = SearchAlgorithms.linearSearchByName(products, "Product 500");
        System.out.println("Found " + nameResults.size() + " products");
        
        // Test searching by category
        System.out.println("\nSearching for products in category 'Electronics':");
        List<Product> categoryResults = SearchAlgorithms.searchByCategory(products, "Electronics");
        System.out.println("Found " + categoryResults.size() + " products");
        
        // Print analysis
        System.out.println("\nAlgorithm Analysis");
        System.out.println("=================");
        System.out.println("Linear Search (O(n)):");
        System.out.println("- Best case: O(1) - First element is the target");
        System.out.println("- Average case: O(n/2) - Target is in the middle");
        System.out.println("- Worst case: O(n) - Last element is the target or target not found");
        
        System.out.println("\nBinary Search (O(log n)):");
        System.out.println("- Best case: O(1) - Middle element is the target");
        System.out.println("- Average case: O(log n) - Typical case");
        System.out.println("- Worst case: O(log n) - Target not found");
        
        System.out.println("\nConclusion:");
        System.out.println("- Binary Search is more efficient for large datasets when searching by ID");
        System.out.println("- Binary Search requires sorted data");
        System.out.println("- Linear Search is necessary for searching by name or category");
        System.out.println("- Consider implementing database indexing for production use");
    }
    
    private static List<Product> generateSampleProducts(int count) {
        List<Product> products = new ArrayList<>();
        Random random = new Random();
        String[] categories = {"Electronics", "Clothing", "Books", "Home", "Sports"};
        
        for (int i = 0; i < count; i++) {
            String category = categories[random.nextInt(categories.length)];
            products.add(new Product(
                i,                          // productId
                "Product " + i,             // productName
                category,                   // category
                10.0 + random.nextDouble() * 990.0  // price between 10.0 and 1000.0
            ));
        }
        
        return products;
    }
} 