package com.workshop4.helloworldbackend.infrastructure.persistence.mapper;

import com.workshop4.helloworldbackend.domain.model.User;
import com.workshop4.helloworldbackend.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper between Domain Model and JPA Entity
 * Keeps domain layer independent from infrastructure
 */
@Component
public class UserEntityMapper {
    
    /**
     * Convert Domain Model to JPA Entity
     */
    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setMemberId(user.getMemberId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
        entity.setBirthDate(user.getBirthDate());
        entity.setGender(user.getGender());
        entity.setAddress(user.getAddress());
        entity.setCity(user.getCity());
        entity.setCountry(user.getCountry());
        entity.setPostalCode(user.getPostalCode());
        entity.setBio(user.getBio());
        entity.setAvatarUrl(user.getAvatarUrl());
        entity.setMembershipLevel(user.getMembershipLevel());
        entity.setPoints(user.getPoints());
        entity.setRegistrationDate(user.getRegistrationDate());
        entity.setIsActive(user.getIsActive());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        
        return entity;
    }
    
    /**
     * Convert JPA Entity to Domain Model
     */
    public User toDomainModel(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        
        User user = new User();
        user.setId(entity.getId());
        user.setMemberId(entity.getMemberId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setPhone(entity.getPhone());
        user.setBirthDate(entity.getBirthDate());
        user.setGender(entity.getGender());
        user.setAddress(entity.getAddress());
        user.setCity(entity.getCity());
        user.setCountry(entity.getCountry());
        user.setPostalCode(entity.getPostalCode());
        user.setBio(entity.getBio());
        user.setAvatarUrl(entity.getAvatarUrl());
        user.setMembershipLevel(entity.getMembershipLevel());
        user.setPoints(entity.getPoints());
        user.setRegistrationDate(entity.getRegistrationDate());
        user.setIsActive(entity.getIsActive());
        user.setCreatedAt(entity.getCreatedAt());
        user.setUpdatedAt(entity.getUpdatedAt());
        
        return user;
    }
}
