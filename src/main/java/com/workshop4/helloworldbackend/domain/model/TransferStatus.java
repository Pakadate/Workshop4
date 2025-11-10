package com.workshop4.helloworldbackend.domain.model;

/**
 * Transfer Status Enum
 * Represents the lifecycle states of a points transfer
 */
public enum TransferStatus {
    PENDING,      // Transfer created but not yet processed
    PROCESSING,   // Transfer is being processed
    COMPLETED,    // Transfer successfully completed
    FAILED,       // Transfer failed (e.g., insufficient points)
    CANCELLED,    // Transfer cancelled before completion
    REVERSED      // Transfer reversed after completion
}
