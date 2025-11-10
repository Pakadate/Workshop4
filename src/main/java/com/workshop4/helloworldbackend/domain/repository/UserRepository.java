package com.workshop4.helloworldbackend.domain.repository;

import com.workshop4.helloworldbackend.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Domain Repository Interface - Defines contract for data access
 * Infrastructure layer will implement this interface
 */
public interface UserRepository {
    
    User save(User user);
    
    Optional<User> findById(Long id);
    
    Optional<User> findByEmail(String email);
    
    List<User> findAll();
    
    List<User> findByIsActiveTrue();
    
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);
    
    List<User> searchUsers(String firstName, String lastName, String email, 
                           String city, Boolean isActive);
    
    boolean existsByEmail(String email);
    
    void deleteById(Long id);
    
    long count();
    
    long countByIsActive(Boolean isActive);
}
