package com.workshop4.helloworldbackend.infrastructure.persistence.adapter;

import com.workshop4.helloworldbackend.domain.model.Transfer;
import com.workshop4.helloworldbackend.domain.repository.TransferRepository;
import com.workshop4.helloworldbackend.infrastructure.persistence.entity.TransferEntity;
import com.workshop4.helloworldbackend.infrastructure.persistence.mapper.TransferEntityMapper;
import com.workshop4.helloworldbackend.infrastructure.persistence.repository.JpaTransferRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Transfer Repository Adapter
 * Implements domain TransferRepository using JPA
 */
@Component
public class TransferRepositoryAdapter implements TransferRepository {
    
    private final JpaTransferRepository jpaRepository;
    private final TransferEntityMapper mapper;
    
    public TransferRepositoryAdapter(JpaTransferRepository jpaRepository, TransferEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public Transfer save(Transfer transfer) {
        TransferEntity entity = mapper.toEntity(transfer);
        TransferEntity saved = jpaRepository.save(entity);
        return mapper.toDomainModel(saved);
    }
    
    @Override
    public Optional<Transfer> findByIdemKey(String idemKey) {
        return jpaRepository.findByIdemKey(idemKey)
            .map(mapper::toDomainModel);
    }
    
    @Override
    public Optional<Transfer> findByTransferId(Long transferId) {
        return jpaRepository.findById(transferId)
            .map(mapper::toDomainModel);
    }
    
    @Override
    public List<Transfer> findByUserId(Long userId, int page, int pageSize) {
        // Page is 1-based in our API, but Spring Data uses 0-based
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return jpaRepository.findByUserId(userId, pageable).stream()
            .map(mapper::toDomainModel)
            .collect(Collectors.toList());
    }
    
    @Override
    public long countByUserId(Long userId) {
        return jpaRepository.countByUserId(userId);
    }
    
    @Override
    public List<Transfer> findByFromUserId(Long fromUserId) {
        return jpaRepository.findByFromUserIdOrderByCreatedAtDesc(fromUserId).stream()
            .map(mapper::toDomainModel)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Transfer> findByToUserId(Long toUserId) {
        return jpaRepository.findByToUserIdOrderByCreatedAtDesc(toUserId).stream()
            .map(mapper::toDomainModel)
            .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsByIdemKey(String idemKey) {
        return jpaRepository.existsByIdemKey(idemKey);
    }
}
