package com.workshop4.helloworldbackend.application.mapper;

import com.workshop4.helloworldbackend.application.dto.TransferCreateRequestDTO;
import com.workshop4.helloworldbackend.application.dto.TransferResponseDTO;
import com.workshop4.helloworldbackend.domain.model.Transfer;
import org.springframework.stereotype.Component;

/**
 * Transfer Mapper
 * Maps between Transfer DTOs and Domain Models
 */
@Component
public class TransferMapper {
    
    /**
     * Converts TransferCreateRequestDTO to Domain Transfer
     */
    public Transfer toDomainModel(TransferCreateRequestDTO dto, String idemKey) {
        if (dto == null) {
            return null;
        }
        
        return new Transfer(
            idemKey,
            dto.getFromUserId(),
            dto.getToUserId(),
            dto.getAmount(),
            dto.getNote()
        );
    }
    
    /**
     * Converts Domain Transfer to TransferResponseDTO
     */
    public TransferResponseDTO toResponseDTO(Transfer transfer) {
        if (transfer == null) {
            return null;
        }
        
        TransferResponseDTO dto = new TransferResponseDTO();
        dto.setIdemKey(transfer.getIdemKey());
        dto.setTransferId(transfer.getTransferId());
        dto.setFromUserId(transfer.getFromUserId());
        dto.setToUserId(transfer.getToUserId());
        dto.setAmount(transfer.getAmount());
        dto.setStatus(transfer.getStatus() != null ? transfer.getStatus().name().toLowerCase() : null);
        dto.setNote(transfer.getNote());
        dto.setCreatedAt(transfer.getCreatedAt());
        dto.setUpdatedAt(transfer.getUpdatedAt());
        dto.setCompletedAt(transfer.getCompletedAt());
        dto.setFailReason(transfer.getFailReason());
        
        return dto;
    }
}
