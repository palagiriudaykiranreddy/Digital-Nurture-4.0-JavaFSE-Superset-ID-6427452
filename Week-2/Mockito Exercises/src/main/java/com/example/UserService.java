package com.example;

/**
 * Service class that handles user operations.
 * Depends on UserApi for external data access.
 */
public class UserService {
    
    private final UserApi userApi;
    
    /**
     * Constructor with dependency injection.
     * 
     * @param userApi The external API dependency
     */
    public UserService(UserApi userApi) {
        this.userApi = userApi;
    }
    
    /**
     * Fetches user data through the external API.
     * 
     * @return User data from external source
     */
    public String fetchUserData() {
        return userApi.getUserData();
    }
    
    /**
     * Retrieves a specific user by ID.
     * 
     * @param userId The user ID to retrieve
     * @return User data for the specified ID
     */
    public String getUser(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return userApi.getUserById(userId);
    }
    
    /**
     * Updates user information and returns a formatted response.
     * 
     * @param userId The ID of the user to update
     * @param userData The new user data
     * @return Success or failure message
     */
    public String updateUserInfo(String userId, String userData) {
        if (userId == null || userData == null) {
            return "Update failed: Invalid input";
        }
        
        boolean success = userApi.updateUser(userId, userData);
        return success ? "User updated successfully" : "Update failed";
    }
} 