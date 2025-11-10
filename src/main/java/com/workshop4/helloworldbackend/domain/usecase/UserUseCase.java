package com.workshop4.helloworldbackend.domain.usecase;

import com.workshop4.helloworldbackend.domain.model.User;

import java.util.List;

/**
 * Use Case Interface for User CRUD operations
 * Defines the business operations that can be performed
 */
public interface UserUseCase {
    
    User createUser(User user);
    
    User getUserById(Long id);
    
    User getUserByEmail(String email);
    
    List<User> getAllUsers();
    
    List<User> getActiveUsers();
    
    List<User> searchUsers(String search);
    
    List<User> searchUsersByCriteria(String firstName, String lastName, 
                                      String email, String city, Boolean isActive);
    
    User updateUser(Long id, User user);
    
    User partialUpdateUser(Long id, User user);
    
    void deleteUser(Long id);
    
    User activateUser(Long id);
    
    User deactivateUser(Long id);
    
    UserStats getUserStats();
    
    /**
     * Inner class for user statistics
     */
    class UserStats {
        private long totalUsers;
        private long activeUsers;
        private long inactiveUsers;

        public UserStats(long totalUsers, long activeUsers, long inactiveUsers) {
            this.totalUsers = totalUsers;
            this.activeUsers = activeUsers;
            this.inactiveUsers = inactiveUsers;
        }

        public long getTotalUsers() {
            return totalUsers;
        }

        public long getActiveUsers() {
            return activeUsers;
        }

        public long getInactiveUsers() {
            return inactiveUsers;
        }
    }
}
