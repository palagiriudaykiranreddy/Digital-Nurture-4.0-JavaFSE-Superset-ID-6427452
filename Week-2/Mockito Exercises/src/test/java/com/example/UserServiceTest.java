package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test class for UserService using Mockito framework.
 */
public class UserServiceTest {
    
    @Mock
    private UserApi mockUserApi;
    
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(mockUserApi);
    }
    
    // Tests for mocking and stubbing functionality
    
    @Test
    @DisplayName("Should return mocked user data when fetching")
    void testFetchUserData_WithMockingAndStubbing() {
        // Arrange
        when(mockUserApi.getUserData()).thenReturn("Mock User Data");
        
        // Act
        String result = userService.fetchUserData();
        
        // Assert
        assertEquals("Mock User Data", result);
    }
    
    @Test
    @DisplayName("Should return specific user data when requesting by ID")
    void testGetUserById_WithParameterizedStubbing() {
        // Arrange
        String userId = "user123";
        String expectedUserData = "John Doe - Engineer";
        when(mockUserApi.getUserById(userId)).thenReturn(expectedUserData);
        
        // Act
        String result = userService.getUser(userId);
        
        // Assert
        assertEquals(expectedUserData, result);
    }
    
    @Test
    @DisplayName("Should handle successful user update")
    void testUpdateUser_WithBooleanStubbing() {
        // Arrange
        String userId = "user456";
        String userData = "Updated User Info";
        when(mockUserApi.updateUser(userId, userData)).thenReturn(true);
        
        // Act
        String result = userService.updateUserInfo(userId, userData);
        
        // Assert
        assertEquals("User updated successfully", result);
    }
    
    // Tests for verifying method interactions
    
    @Test
    @DisplayName("Should verify that getUserData is called exactly once")
    void testVerifyMethodCall() {
        // Arrange
        when(mockUserApi.getUserData()).thenReturn("Test Data");
        
        // Act
        userService.fetchUserData();
        
        // Assert
        verify(mockUserApi).getUserData();
    }
    
    @Test
    @DisplayName("Should verify method called with specific arguments")
    void testVerifyMethodCallWithArguments() {
        // Arrange
        String userId = "testUser";
        when(mockUserApi.getUserById(userId)).thenReturn("Test User");
        
        // Act
        userService.getUser(userId);
        
        // Assert
        verify(mockUserApi).getUserById("testUser");
    }
    
    @Test
    @DisplayName("Should verify multiple interactions in correct order")
    void testVerifyMultipleInteractions() {
        // Arrange
        when(mockUserApi.getUserById("user1")).thenReturn("User 1");
        when(mockUserApi.updateUser("user1", "New Data")).thenReturn(true);
        
        // Act
        userService.getUser("user1");
        userService.updateUserInfo("user1", "New Data");
        
        // Assert
        verify(mockUserApi).getUserById("user1");
        verify(mockUserApi).updateUser("user1", "New Data");
    }
    
    @Test
    @DisplayName("Should verify method never called")
    void testVerifyMethodNeverCalled() {
        // Arrange and Act
        when(mockUserApi.getUserData()).thenReturn("Data");
        userService.fetchUserData();
        
        // Assert
        verify(mockUserApi, never()).getUserById(anyString());
        verify(mockUserApi, never()).updateUser(anyString(), anyString());
    }
    
    @Test
    @DisplayName("Should verify method called specific number of times")
    void testVerifyMethodCallCount() {
        // Arrange
        when(mockUserApi.getUserData()).thenReturn("Data");
        
        // Act
        userService.fetchUserData();
        userService.fetchUserData();
        
        // Assert
        verify(mockUserApi, times(2)).getUserData();
    }
    
    // Tests for edge cases and error handling
    
    @Test
    @DisplayName("Should handle null user ID appropriately")
    void testGetUser_WithNullUserId() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.getUser(null);
        });
        
        verify(mockUserApi, never()).getUserById(anyString());
    }
    
    @Test
    @DisplayName("Should handle failed update operation")
    void testUpdateUser_WhenUpdateFails() {
        // Arrange
        when(mockUserApi.updateUser("user1", "data")).thenReturn(false);
        
        // Act
        String result = userService.updateUserInfo("user1", "data");
        
        // Assert
        assertEquals("Update failed", result);
        verify(mockUserApi).updateUser("user1", "data");
    }
} 