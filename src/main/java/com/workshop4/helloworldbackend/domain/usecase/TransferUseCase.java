package com.workshop4.helloworldbackend.domain.usecase;

import com.workshop4.helloworldbackend.domain.model.Transfer;
import java.util.Optional;

/**
 * Transfer Use Case Interface
 * Defines business operations for points transfer between users
 */
public interface TransferUseCase {
    
    /**
     * Creates a new points transfer between users
     * Validates users exist, sender has enough points, and executes atomically
     * 
     * @param fromUserId The user ID sending points
     * @param toUserId The user ID receiving points
     * @param amount The amount of points to transfer
     * @param note Optional note for the transfer
     * @return The created transfer with generated idempotency key
     */
    Transfer createTransfer(Long fromUserId, Long toUserId, Integer amount, String note);
    
    /**
     * Gets a transfer by its idempotency key
     * 
     * @param idemKey The idempotency key (same as transfer ID in the API)
     * @return Optional containing the transfer if found
     */
    Optional<Transfer> getTransferByIdemKey(String idemKey);
    
    /**
     * Gets all transfers involving a specific user (as sender or receiver)
     * 
     * @param userId The user ID to filter by
     * @param page Page number (1-based)
     * @param pageSize Number of items per page
     * @return TransferListResult containing transfers and pagination info
     */
    TransferListResult getTransfersByUserId(Long userId, int page, int pageSize);
    
    /**
     * Result object for paginated transfer list
     */
    class TransferListResult {
        private final java.util.List<Transfer> data;
        private final int page;
        private final int pageSize;
        private final long total;
        
        public TransferListResult(java.util.List<Transfer> data, int page, int pageSize, long total) {
            this.data = data;
            this.page = page;
            this.pageSize = pageSize;
            this.total = total;
        }
        
        public java.util.List<Transfer> getData() {
            return data;
        }
        
        public int getPage() {
            return page;
        }
        
        public int getPageSize() {
            return pageSize;
        }
        
        public long getTotal() {
            return total;
        }
    }
}
