package com.workshop4.helloworldbackend.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Transfer Create Request DTO
 * Used for creating new transfer requests
 */
public class TransferCreateRequestDTO {
    
    @NotNull(message = "fromUserId is required")
    @Min(value = 1, message = "fromUserId must be positive")
    private Long fromUserId;
    
    @NotNull(message = "toUserId is required")
    @Min(value = 1, message = "toUserId must be positive")
    private Long toUserId;
    
    @NotNull(message = "amount is required")
    @Min(value = 1, message = "amount must be positive")
    private Integer amount;
    
    @Size(max = 512, message = "note cannot exceed 512 characters")
    private String note;
    
    // Constructors
    public TransferCreateRequestDTO() {
    }
    
    public TransferCreateRequestDTO(Long fromUserId, Long toUserId, Integer amount, String note) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.note = note;
    }
    
    // Getters and Setters
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
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
}
