package com.workshop4.helloworldbackend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Transfer JPA Entity
 * Represents transfer table in the database
 */
@Entity
@Table(name = "transfers")
public class TransferEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;
    
    @Column(nullable = false, unique = true, length = 128)
    private String idemKey;
    
    @Column(nullable = false)
    private Long fromUserId;
    
    @Column(nullable = false)
    private Long toUserId;
    
    @Column(nullable = false)
    private Integer amount;
    
    @Column(nullable = false, length = 20)
    private String status;
    
    @Column(length = 512)
    private String note;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    @Column
    private LocalDateTime completedAt;
    
    @Column(length = 512)
    private String failReason;
    
    // Constructors
    public TransferEntity() {
    }
    
    // Getters and Setters
    public Long getTransferId() {
        return transferId;
    }
    
    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }
    
    public String getIdemKey() {
        return idemKey;
    }
    
    public void setIdemKey(String idemKey) {
        this.idemKey = idemKey;
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
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
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
}
