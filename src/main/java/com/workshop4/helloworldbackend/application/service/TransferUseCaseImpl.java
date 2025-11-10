package com.workshop4.helloworldbackend.application.service;

import com.workshop4.helloworldbackend.domain.model.Transfer;
import com.workshop4.helloworldbackend.domain.model.User;
import com.workshop4.helloworldbackend.domain.repository.TransferRepository;
import com.workshop4.helloworldbackend.domain.repository.UserRepository;
import com.workshop4.helloworldbackend.domain.usecase.TransferUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Transfer Use Case Implementation
 * Implements business logic for points transfer operations
 */
@Service
public class TransferUseCaseImpl implements TransferUseCase {
    
    private final TransferRepository transferRepository;
    private final UserRepository userRepository;
    
    public TransferUseCaseImpl(TransferRepository transferRepository, UserRepository userRepository) {
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional
    public Transfer createTransfer(Long fromUserId, Long toUserId, Integer amount, String note) {
        // Generate idempotency key
        String idemKey = UUID.randomUUID().toString();
        
        // Create transfer object
        Transfer transfer = new Transfer(idemKey, fromUserId, toUserId, amount, note);
        
        // Validate transfer business rules
        transfer.validate();
        
        // Validate sender exists and is active
        User sender = userRepository.findById(fromUserId)
            .orElseThrow(() -> new IllegalArgumentException("Sender user not found: " + fromUserId));
        
        if (!sender.getIsActive()) {
            transfer.fail("Sender user is not active");
            transferRepository.save(transfer);
            throw new IllegalStateException("Sender user is not active");
        }
        
        // Validate receiver exists and is active
        User receiver = userRepository.findById(toUserId)
            .orElseThrow(() -> new IllegalArgumentException("Receiver user not found: " + toUserId));
        
        if (!receiver.getIsActive()) {
            transfer.fail("Receiver user is not active");
            transferRepository.save(transfer);
            throw new IllegalStateException("Receiver user is not active");
        }
        
        // Check if sender has enough points
        if (sender.getPoints() < amount) {
            transfer.fail("Insufficient points");
            transferRepository.save(transfer);
            throw new IllegalStateException("Insufficient points. Available: " + sender.getPoints() + ", Required: " + amount);
        }
        
        // Mark as processing
        transfer.markAsProcessing();
        
        // Execute the transfer atomically
        try {
            // Deduct points from sender
            sender.deductPoints(amount);
            userRepository.save(sender);
            
            // Add points to receiver
            receiver.addPoints(amount);
            userRepository.save(receiver);
            
            // Mark transfer as completed
            transfer.complete();
            
        } catch (Exception e) {
            // If anything fails, mark transfer as failed
            transfer.fail("Transfer execution failed: " + e.getMessage());
            throw new RuntimeException("Transfer execution failed", e);
        }
        
        // Save and return the transfer
        return transferRepository.save(transfer);
    }
    
    @Override
    public Optional<Transfer> getTransferByIdemKey(String idemKey) {
        return transferRepository.findByIdemKey(idemKey);
    }
    
    @Override
    public TransferListResult getTransfersByUserId(Long userId, int page, int pageSize) {
        // Validate pagination parameters
        if (page < 1) {
            throw new IllegalArgumentException("page must be >= 1");
        }
        if (pageSize < 1 || pageSize > 200) {
            throw new IllegalArgumentException("pageSize must be between 1 and 200");
        }
        
        // Get transfers and total count
        var transfers = transferRepository.findByUserId(userId, page, pageSize);
        long total = transferRepository.countByUserId(userId);
        
        return new TransferListResult(transfers, page, pageSize, total);
    }
}
