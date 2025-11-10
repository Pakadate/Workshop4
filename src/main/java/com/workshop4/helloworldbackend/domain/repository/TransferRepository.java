package com.workshop4.helloworldbackend.domain.repository;

import com.workshop4.helloworldbackend.domain.model.Transfer;
import java.util.List;
import java.util.Optional;

/**
 * Transfer Repository Interface
 * Defines contract for transfer data persistence operations
 */
public interface TransferRepository {
    
    /**
     * Saves a transfer (create or update)
     */
    Transfer save(Transfer transfer);
    
    /**
     * Finds a transfer by its idempotency key
     */
    Optional<Transfer> findByIdemKey(String idemKey);
    
    /**
     * Finds a transfer by its internal transfer ID
     */
    Optional<Transfer> findByTransferId(Long transferId);
    
    /**
     * Finds all transfers involving a specific user (as sender or receiver)
     * with pagination
     */
    List<Transfer> findByUserId(Long userId, int page, int pageSize);
    
    /**
     * Counts total transfers involving a specific user
     */
    long countByUserId(Long userId);
    
    /**
     * Finds all transfers from a specific user
     */
    List<Transfer> findByFromUserId(Long fromUserId);
    
    /**
     * Finds all transfers to a specific user
     */
    List<Transfer> findByToUserId(Long toUserId);
    
    /**
     * Checks if an idempotency key already exists
     */
    boolean existsByIdemKey(String idemKey);
}
