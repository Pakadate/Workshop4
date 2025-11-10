package com.workshop4.helloworldbackend.infrastructure.persistence.mapper;

import com.workshop4.helloworldbackend.domain.model.Transfer;
import com.workshop4.helloworldbackend.domain.model.TransferStatus;
import com.workshop4.helloworldbackend.infrastructure.persistence.entity.TransferEntity;
import org.springframework.stereotype.Component;

/**
 * Transfer Entity Mapper
 * Maps between Domain Transfer and JPA TransferEntity
 */
@Component
public class TransferEntityMapper {
    
    /**
     * Converts Domain Transfer to JPA TransferEntity
     */
    public TransferEntity toEntity(Transfer transfer) {
        if (transfer == null) {
            return null;
        }
        
        TransferEntity entity = new TransferEntity();
        entity.setTransferId(transfer.getTransferId());
        entity.setIdemKey(transfer.getIdemKey());
        entity.setFromUserId(transfer.getFromUserId());
        entity.setToUserId(transfer.getToUserId());
        entity.setAmount(transfer.getAmount());
        entity.setStatus(transfer.getStatus() != null ? transfer.getStatus().name() : null);
        entity.setNote(transfer.getNote());
        entity.setCreatedAt(transfer.getCreatedAt());
        entity.setUpdatedAt(transfer.getUpdatedAt());
        entity.setCompletedAt(transfer.getCompletedAt());
        entity.setFailReason(transfer.getFailReason());
        
        return entity;
    }
    
    /**
     * Converts JPA TransferEntity to Domain Transfer
     */
    public Transfer toDomainModel(TransferEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Transfer transfer = new Transfer();
        transfer.setTransferId(entity.getTransferId());
        transfer.setIdemKey(entity.getIdemKey());
        transfer.setFromUserId(entity.getFromUserId());
        transfer.setToUserId(entity.getToUserId());
        transfer.setAmount(entity.getAmount());
        transfer.setStatus(entity.getStatus() != null ? TransferStatus.valueOf(entity.getStatus()) : null);
        transfer.setNote(entity.getNote());
        transfer.setCreatedAt(entity.getCreatedAt());
        transfer.setUpdatedAt(entity.getUpdatedAt());
        transfer.setCompletedAt(entity.getCompletedAt());
        transfer.setFailReason(entity.getFailReason());
        
        return transfer;
    }
}
