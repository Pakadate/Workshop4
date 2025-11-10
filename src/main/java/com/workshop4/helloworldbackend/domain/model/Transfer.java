package com.workshop4.helloworldbackend.domain.model;

import java.time.LocalDateTime;

/**
 * Domain Model: Transfer
 * Pure business entity representing a points transfer between users
 * Contains business logic and validation rules
 */
public class Transfer {
    private String idemKey;
    private Long transferId;
    private Long fromUserId;
    private Long toUserId;
    private Integer amount;
    private TransferStatus status;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
    private String failReason;

    // Constructors
    public Transfer() {
    }

    public Transfer(String idemKey, Long fromUserId, Long toUserId, Integer amount, String note) {
        this.idemKey = idemKey;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.note = note;
        this.status = TransferStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Business Logic Methods
    
    /**
     * Validates if the transfer request is valid
     */
    public void validate() {
        if (fromUserId == null || fromUserId <= 0) {
            throw new IllegalArgumentException("fromUserId must be positive");
        }
        if (toUserId == null || toUserId <= 0) {
            throw new IllegalArgumentException("toUserId must be positive");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("Cannot transfer to yourself");
        }
        if (note != null && note.length() > 512) {
            throw new IllegalArgumentException("note cannot exceed 512 characters");
        }
    }

    /**
     * Marks transfer as processing
     */
    public void markAsProcessing() {
        if (this.status != TransferStatus.PENDING) {
            throw new IllegalStateException("Can only process pending transfers");
        }
        this.status = TransferStatus.PROCESSING;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Marks transfer as completed
     */
    public void complete() {
        if (this.status != TransferStatus.PROCESSING && this.status != TransferStatus.PENDING) {
            throw new IllegalStateException("Can only complete processing/pending transfers");
        }
        this.status = TransferStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Marks transfer as failed with reason
     */
    public void fail(String reason) {
        this.status = TransferStatus.FAILED;
        this.failReason = reason;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Marks transfer as cancelled
     */
    public void cancel() {
        if (this.status == TransferStatus.COMPLETED) {
            throw new IllegalStateException("Cannot cancel completed transfer");
        }
        this.status = TransferStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Marks transfer as reversed (after completion)
     */
    public void reverse() {
        if (this.status != TransferStatus.COMPLETED) {
            throw new IllegalStateException("Can only reverse completed transfers");
        }
        this.status = TransferStatus.REVERSED;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Checks if transfer is in a final state
     */
    public boolean isFinal() {
        return status == TransferStatus.COMPLETED || 
               status == TransferStatus.FAILED || 
               status == TransferStatus.CANCELLED ||
               status == TransferStatus.REVERSED;
    }

    /**
     * Checks if transfer involves a specific user (as sender or receiver)
     */
    public boolean involvesUser(Long userId) {
        return fromUserId.equals(userId) || toUserId.equals(userId);
    }

    // Getters and Setters
    public String getIdemKey() {
        return idemKey;
    }

    public void setIdemKey(String idemKey) {
        this.idemKey = idemKey;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "idemKey='" + idemKey + '\'' +
                ", transferId=" + transferId +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", amount=" + amount +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
