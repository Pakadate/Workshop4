package com.workshop4.helloworldbackend.repository;

import com.workshop4.helloworldbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find user by email
    Optional<User> findByEmail(String email);
    
    // Check if user exists by email
    boolean existsByEmail(String email);
    
    // Find all active users
    List<User> findByIsActiveTrue();
    
    // Find users by first name or last name (case-insensitive)
    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByNameContainingIgnoreCase(@Param("name") String name);
    
    // Find users by city
    List<User> findByCityIgnoreCase(String city);
    
    // Find users by country
    List<User> findByCountryIgnoreCase(String country);
    
    // Custom query to search users by multiple criteria
    @Query("SELECT u FROM User u WHERE " +
           "(:firstName IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
           "(:lastName IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
           "(:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
           "(:city IS NULL OR LOWER(u.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
           "(:isActive IS NULL OR u.isActive = :isActive)")
    List<User> searchUsers(@Param("firstName") String firstName,
                          @Param("lastName") String lastName,
                          @Param("email") String email,
                          @Param("city") String city,
                          @Param("isActive") Boolean isActive);
}