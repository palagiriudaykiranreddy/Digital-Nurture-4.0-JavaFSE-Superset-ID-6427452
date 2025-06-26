package com.example;

/**
 * Interface representing an external API for user operations.
 * In a real application, this might be a REST client or database access layer.
 */
public interface UserApi {
    
    /**
     * Retrieves user data from external source.
     * 
     * @return User data as a string
     */
    String getUserData();
    
    /**
     * Retrieves user data by specific ID.
     * 
     * @param userId The ID of the user to retrieve
     * @return User data for the specified ID
     */
    String getUserById(String userId);
    
    /**
     * Updates user information in the external system.
     * 
     * @param userId The ID of the user to update
     * @param userData The new user data
     * @return true if update was successful, false otherwise
     */
    boolean updateUser(String userId, String userData);
} 