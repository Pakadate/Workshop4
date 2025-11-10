package com.workshop4.helloworldbackend.infrastructure.persistence.adapter;

import com.workshop4.helloworldbackend.domain.model.User;
import com.workshop4.helloworldbackend.domain.repository.UserRepository;
import com.workshop4.helloworldbackend.infrastructure.persistence.entity.UserEntity;
import com.workshop4.helloworldbackend.infrastructure.persistence.mapper.UserEntityMapper;
import com.workshop4.helloworldbackend.infrastructure.persistence.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository Adapter - Implements domain repository using JPA
 * This is the bridge between domain layer and infrastructure layer
 */
@Component
public class UserRepositoryAdapter implements UserRepository {
    
    private final JpaUserRepository jpaRepository;
    private final UserEntityMapper mapper;
    
    public UserRepositoryAdapter(JpaUserRepository jpaRepository, UserEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    
    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomainModel(savedEntity);
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomainModel);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomainModel);
    }
    
    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<User> findByIsActiveTrue() {
        return jpaRepository.findByIsActiveTrue().stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName) {
        return jpaRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstName, lastName)
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<User> searchUsers(String firstName, String lastName, String email, 
                                  String city, Boolean isActive) {
        return jpaRepository.searchUsers(firstName, lastName, email, city, isActive)
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
    
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
    
    @Override
    public long count() {
        return jpaRepository.count();
    }
    
    @Override
    public long countByIsActive(Boolean isActive) {
        return jpaRepository.countByIsActive(isActive);
    }
}
