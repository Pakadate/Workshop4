package com.workshop4.helloworldbackend.service;

import com.workshop4.helloworldbackend.entity.User;
import com.workshop4.helloworldbackend.exception.ResourceNotFoundException;
import com.workshop4.helloworldbackend.exception.DuplicateResourceException;
import com.workshop4.helloworldbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    // Get all active users
    public List<User> getAllActiveUsers() {
        logger.info("Fetching all active users");
        return userRepository.findByIsActiveTrue();
    }

    // Get user by ID
    public User getUserById(Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // Get user by email
    public Optional<User> getUserByEmail(String email) {
        logger.info("Fetching user with email: {}", email);
        return userRepository.findByEmail(email);
    }

    // Create new user
    public User createUser(User user) {
        logger.info("Creating new user with email: {}", user.getEmail());
        
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("User already exists with email: " + user.getEmail());
        }
        
        // Set default values
        if (user.getIsActive() == null) {
            user.setIsActive(true);
        }
        
        User savedUser = userRepository.save(user);
        logger.info("User created successfully with ID: {}", savedUser.getId());
        return savedUser;
    }

    // Update existing user
    public User updateUser(Long id, User userDetails) {
        logger.info("Updating user with ID: {}", id);
        
        User existingUser = getUserById(id);
        
        // Check if email is being changed and if it already exists
        if (!existingUser.getEmail().equals(userDetails.getEmail()) && 
            userRepository.existsByEmail(userDetails.getEmail())) {
            throw new DuplicateResourceException("User already exists with email: " + userDetails.getEmail());
        }
        
        // Update fields
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPhone(userDetails.getPhone());
        existingUser.setBirthDate(userDetails.getBirthDate());
        existingUser.setGender(userDetails.getGender());
        existingUser.setAddress(userDetails.getAddress());
        existingUser.setCity(userDetails.getCity());
        existingUser.setCountry(userDetails.getCountry());
        existingUser.setPostalCode(userDetails.getPostalCode());
        existingUser.setBio(userDetails.getBio());
        existingUser.setAvatarUrl(userDetails.getAvatarUrl());
        
        if (userDetails.getIsActive() != null) {
            existingUser.setIsActive(userDetails.getIsActive());
        }
        
        User updatedUser = userRepository.save(existingUser);
        logger.info("User updated successfully with ID: {}", updatedUser.getId());
        return updatedUser;
    }

    // Partial update user
    public User partialUpdateUser(Long id, User userDetails) {
        logger.info("Partial updating user with ID: {}", id);
        
        User existingUser = getUserById(id);
        
        // Update only non-null fields
        if (userDetails.getFirstName() != null) {
            existingUser.setFirstName(userDetails.getFirstName());
        }
        if (userDetails.getLastName() != null) {
            existingUser.setLastName(userDetails.getLastName());
        }
        if (userDetails.getEmail() != null) {
            // Check if email is being changed and if it already exists
            if (!existingUser.getEmail().equals(userDetails.getEmail()) && 
                userRepository.existsByEmail(userDetails.getEmail())) {
                throw new DuplicateResourceException("User already exists with email: " + userDetails.getEmail());
            }
            existingUser.setEmail(userDetails.getEmail());
        }
        if (userDetails.getPhone() != null) {
            existingUser.setPhone(userDetails.getPhone());
        }
        if (userDetails.getBirthDate() != null) {
            existingUser.setBirthDate(userDetails.getBirthDate());
        }
        if (userDetails.getGender() != null) {
            existingUser.setGender(userDetails.getGender());
        }
        if (userDetails.getAddress() != null) {
            existingUser.setAddress(userDetails.getAddress());
        }
        if (userDetails.getCity() != null) {
            existingUser.setCity(userDetails.getCity());
        }
        if (userDetails.getCountry() != null) {
            existingUser.setCountry(userDetails.getCountry());
        }
        if (userDetails.getPostalCode() != null) {
            existingUser.setPostalCode(userDetails.getPostalCode());
        }
        if (userDetails.getBio() != null) {
            existingUser.setBio(userDetails.getBio());
        }
        if (userDetails.getAvatarUrl() != null) {
            existingUser.setAvatarUrl(userDetails.getAvatarUrl());
        }
        if (userDetails.getIsActive() != null) {
            existingUser.setIsActive(userDetails.getIsActive());
        }
        
        User updatedUser = userRepository.save(existingUser);
        logger.info("User partial updated successfully with ID: {}", updatedUser.getId());
        return updatedUser;
    }

    // Delete user (hard delete)
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);
        
        User user = getUserById(id);
        userRepository.delete(user);
        
        logger.info("User deleted successfully with ID: {}", id);
    }

    // Soft delete user (deactivate)
    public User deactivateUser(Long id) {
        logger.info("Deactivating user with ID: {}", id);
        
        User user = getUserById(id);
        user.setIsActive(false);
        
        User deactivatedUser = userRepository.save(user);
        logger.info("User deactivated successfully with ID: {}", id);
        return deactivatedUser;
    }

    // Activate user
    public User activateUser(Long id) {
        logger.info("Activating user with ID: {}", id);
        
        User user = getUserById(id);
        user.setIsActive(true);
        
        User activatedUser = userRepository.save(user);
        logger.info("User activated successfully with ID: {}", id);
        return activatedUser;
    }

    // Search users
    public List<User> searchUsers(String firstName, String lastName, String email, String city, Boolean isActive) {
        logger.info("Searching users with criteria - firstName: {}, lastName: {}, email: {}, city: {}, isActive: {}", 
                   firstName, lastName, email, city, isActive);
        return userRepository.searchUsers(firstName, lastName, email, city, isActive);
    }

    // Search users by name
    public List<User> searchUsersByName(String name) {
        logger.info("Searching users by name: {}", name);
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}