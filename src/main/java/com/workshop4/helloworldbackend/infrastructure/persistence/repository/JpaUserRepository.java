package com.workshop4.helloworldbackend.infrastructure.persistence.repository;

import com.workshop4.helloworldbackend.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA Repository
 * Infrastructure layer - Database access
 */
@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    List<UserEntity> findByIsActiveTrue();
    
    List<UserEntity> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);
    
    @Query("SELECT u FROM UserEntity u WHERE " +
           "(:firstName IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
           "(:lastName IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
           "(:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) AND " +
           "(:city IS NULL OR LOWER(u.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
           "(:isActive IS NULL OR u.isActive = :isActive)")
    List<UserEntity> searchUsers(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("city") String city,
            @Param("isActive") Boolean isActive
    );
    
    long countByIsActive(Boolean isActive);
}
