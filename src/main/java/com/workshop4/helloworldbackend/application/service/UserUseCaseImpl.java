package com.workshop4.helloworldbackend.application.service;

import com.workshop4.helloworldbackend.domain.model.User;
import com.workshop4.helloworldbackend.domain.repository.UserRepository;
import com.workshop4.helloworldbackend.domain.usecase.UserUseCase;
import com.workshop4.helloworldbackend.exception.DuplicateResourceException;
import com.workshop4.helloworldbackend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Use Case Implementation - Contains business logic
 * This is part of Application Layer
 */
@Service
@Transactional
public class UserUseCaseImpl implements UserUseCase {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User createUser(User user) {
        // Business rule: Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException(
                "User already exists with email: " + user.getEmail()
            );
        }
        
        // Business rule: Set default values
        if (user.getIsActive() == null) {
            user.setIsActive(true);
        }
        if (user.getPoints() == null) {
            user.setPoints(0);
        }
        
        return userRepository.save(user);
    }
    
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "User not found with id: " + id
            ));
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException(
                "User not found with email: " + email
            ));
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public List<User> getActiveUsers() {
        return userRepository.findByIsActiveTrue();
    }
    
    @Override
    public List<User> searchUsers(String search) {
        if (search == null || search.trim().isEmpty()) {
            return userRepository.findAll();
        }
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            search, search
        );
    }
    
    @Override
    public List<User> searchUsersByCriteria(String firstName, String lastName, 
                                            String email, String city, Boolean isActive) {
        return userRepository.searchUsers(firstName, lastName, email, city, isActive);
    }
    
    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        
        // Business rule: Check email uniqueness if email is being changed
        if (!existingUser.getEmail().equals(updatedUser.getEmail())) {
            if (userRepository.existsByEmail(updatedUser.getEmail())) {
                throw new DuplicateResourceException(
                    "User already exists with email: " + updatedUser.getEmail()
                );
            }
        }
        
        // Update all fields
        existingUser.setMemberId(updatedUser.getMemberId());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setBirthDate(updatedUser.getBirthDate());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setCity(updatedUser.getCity());
        existingUser.setCountry(updatedUser.getCountry());
        existingUser.setPostalCode(updatedUser.getPostalCode());
        existingUser.setBio(updatedUser.getBio());
        existingUser.setAvatarUrl(updatedUser.getAvatarUrl());
        existingUser.setMembershipLevel(updatedUser.getMembershipLevel());
        existingUser.setPoints(updatedUser.getPoints());
        existingUser.setRegistrationDate(updatedUser.getRegistrationDate());
        
        return userRepository.save(existingUser);
    }
    
    @Override
    public User partialUpdateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        
        // Update only non-null fields
        if (updatedUser.getMemberId() != null) {
            existingUser.setMemberId(updatedUser.getMemberId());
        }
        if (updatedUser.getFirstName() != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            existingUser.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getEmail() != null && !existingUser.getEmail().equals(updatedUser.getEmail())) {
            if (userRepository.existsByEmail(updatedUser.getEmail())) {
                throw new DuplicateResourceException(
                    "User already exists with email: " + updatedUser.getEmail()
                );
            }
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPhone() != null) {
            existingUser.setPhone(updatedUser.getPhone());
        }
        if (updatedUser.getBirthDate() != null) {
            existingUser.setBirthDate(updatedUser.getBirthDate());
        }
        if (updatedUser.getGender() != null) {
            existingUser.setGender(updatedUser.getGender());
        }
        if (updatedUser.getAddress() != null) {
            existingUser.setAddress(updatedUser.getAddress());
        }
        if (updatedUser.getCity() != null) {
            existingUser.setCity(updatedUser.getCity());
        }
        if (updatedUser.getCountry() != null) {
            existingUser.setCountry(updatedUser.getCountry());
        }
        if (updatedUser.getPostalCode() != null) {
            existingUser.setPostalCode(updatedUser.getPostalCode());
        }
        if (updatedUser.getBio() != null) {
            existingUser.setBio(updatedUser.getBio());
        }
        if (updatedUser.getAvatarUrl() != null) {
            existingUser.setAvatarUrl(updatedUser.getAvatarUrl());
        }
        if (updatedUser.getMembershipLevel() != null) {
            existingUser.setMembershipLevel(updatedUser.getMembershipLevel());
        }
        if (updatedUser.getPoints() != null) {
            existingUser.setPoints(updatedUser.getPoints());
        }
        if (updatedUser.getRegistrationDate() != null) {
            existingUser.setRegistrationDate(updatedUser.getRegistrationDate());
        }
        
        return userRepository.save(existingUser);
    }
    
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
    
    @Override
    public User activateUser(Long id) {
        User user = getUserById(id);
        user.activate();  // Business logic in domain model
        return userRepository.save(user);
    }
    
    @Override
    public User deactivateUser(Long id) {
        User user = getUserById(id);
        user.deactivate();  // Business logic in domain model
        return userRepository.save(user);
    }
    
    @Override
    public UserStats getUserStats() {
        long totalUsers = userRepository.count();
        long activeUsers = userRepository.countByIsActive(true);
        long inactiveUsers = userRepository.countByIsActive(false);
        
        return new UserStats(totalUsers, activeUsers, inactiveUsers);
    }
}
