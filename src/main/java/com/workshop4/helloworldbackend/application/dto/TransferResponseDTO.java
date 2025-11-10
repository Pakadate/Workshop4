package com.workshop4.helloworldbackend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

/**
 * Transfer Response DTO
 * Used for returning transfer information to clients
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferResponseDTO {
    
    private String idemKey;
    private Long transferId;
    private Long fromUserId;
    private Long toUserId;
    private Integer amount;
    private String status;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
    private String failReason;
    
    // Constructors
    public TransferResponseDTO() {
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
