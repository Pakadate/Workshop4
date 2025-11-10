package com.workshop4.helloworldbackend.infrastructure.persistence.repository;

import com.workshop4.helloworldbackend.infrastructure.persistence.entity.TransferEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA Transfer Repository
 * Spring Data JPA repository for TransferEntity
 */
@Repository
public interface JpaTransferRepository extends JpaRepository<TransferEntity, Long> {
    
    /**
     * Find transfer by idempotency key
     */
    Optional<TransferEntity> findByIdemKey(String idemKey);
    
    /**
     * Find transfers involving a specific user (as sender or receiver)
     */
    @Query("SELECT t FROM TransferEntity t WHERE t.fromUserId = :userId OR t.toUserId = :userId ORDER BY t.createdAt DESC")
    List<TransferEntity> findByUserId(@Param("userId") Long userId, Pageable pageable);
    
    /**
     * Count transfers involving a specific user
     */
    @Query("SELECT COUNT(t) FROM TransferEntity t WHERE t.fromUserId = :userId OR t.toUserId = :userId")
    long countByUserId(@Param("userId") Long userId);
    
    /**
     * Find transfers from a specific user
     */
    List<TransferEntity> findByFromUserIdOrderByCreatedAtDesc(Long fromUserId);
    
    /**
     * Find transfers to a specific user
     */
    List<TransferEntity> findByToUserIdOrderByCreatedAtDesc(Long toUserId);
    
    /**
     * Check if idempotency key exists
     */
    boolean existsByIdemKey(String idemKey);
}
