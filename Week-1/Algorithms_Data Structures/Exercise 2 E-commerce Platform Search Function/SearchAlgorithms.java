import java.util.List;
import java.util.ArrayList;

public class SearchAlgorithms {
    
    /**
     * Linear Search implementation
     * Time Complexity: O(n) - where n is the number of products
     */
    public static Product linearSearchById(List<Product> products, int targetId) {
        long startTime = System.nanoTime();
        
        for (Product product : products) {
            if (product.getProductId() == targetId) {
                long endTime = System.nanoTime();
                System.out.printf("Linear Search took %d nanoseconds%n", (endTime - startTime));
                return product;
            }
        }
        
        long endTime = System.nanoTime();
        System.out.printf("Linear Search took %d nanoseconds%n", (endTime - startTime));
        return null;
    }

    /**
     * Binary Search implementation
     * Time Complexity: O(log n) - where n is the number of products
     * Note: Array must be sorted by productId
     */
    public static Product binarySearchById(List<Product> products, int targetId) {
        long startTime = System.nanoTime();
        
        int left = 0;
        int right = products.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = products.get(mid);

            if (midProduct.getProductId() == targetId) {
                long endTime = System.nanoTime();
                System.out.printf("Binary Search took %d nanoseconds%n", (endTime - startTime));
                return midProduct;
            }

            if (midProduct.getProductId() < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        long endTime = System.nanoTime();
        System.out.printf("Binary Search took %d nanoseconds%n", (endTime - startTime));
        return null;
    }

    /**
     * Linear Search by product name (case-insensitive)
     * Time Complexity: O(n) - where n is the number of products
     */
    public static List<Product> linearSearchByName(List<Product> products, String targetName) {
        long startTime = System.nanoTime();
        List<Product> results = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(targetName.toLowerCase())) {
                results.add(product);
            }
        }
        
        long endTime = System.nanoTime();
        System.out.printf("Name Search took %d nanoseconds%n", (endTime - startTime));
        return results;
    }

    /**
     * Linear Search by category
     * Time Complexity: O(n) - where n is the number of products
     */
    public static List<Product> searchByCategory(List<Product> products, String category) {
        long startTime = System.nanoTime();
        List<Product> results = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                results.add(product);
            }
        }
        
        long endTime = System.nanoTime();
        System.out.printf("Category Search took %d nanoseconds%n", (endTime - startTime));
        return results;
    }
} 