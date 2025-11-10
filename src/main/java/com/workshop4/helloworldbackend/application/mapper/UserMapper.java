package com.workshop4.helloworldbackend.application.mapper;

import com.workshop4.helloworldbackend.application.dto.UserDTO;
import com.workshop4.helloworldbackend.application.dto.UserResponseDTO;
import com.workshop4.helloworldbackend.domain.model.User;
import org.springframework.stereotype.Component;

/**
 * Mapper to convert between Domain Model and DTOs
 * Separates presentation layer from domain layer
 */
@Component
public class UserMapper {
    
    /**
     * Convert UserDTO to Domain Model
     */
    public User toDomainModel(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        
        User user = new User();
        user.setId(dto.getId());
        user.setMemberId(dto.getMemberId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setBirthDate(dto.getBirthDate());
        user.setGender(dto.getGender());
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setPostalCode(dto.getPostalCode());
        user.setBio(dto.getBio());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setMembershipLevel(dto.getMembershipLevel());
        user.setPoints(dto.getPoints());
        user.setRegistrationDate(dto.getRegistrationDate());
        user.setIsActive(dto.getIsActive());
        
        return user;
    }
    
    /**
     * Convert Domain Model to UserResponseDTO
     */
    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }
        
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setMemberId(user.getMemberId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setBirthDate(user.getBirthDate());
        dto.setGender(user.getGender());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setCountry(user.getCountry());
        dto.setPostalCode(user.getPostalCode());
        dto.setBio(user.getBio());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setMembershipLevel(user.getMembershipLevel());
        dto.setPoints(user.getPoints());
        dto.setRegistrationDate(user.getRegistrationDate());
        dto.setIsActive(user.getIsActive());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setFullName(user.getFullName());
        
        return dto;
    }
    
    /**
     * Convert Domain Model to UserDTO
     */
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setMemberId(user.getMemberId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setBirthDate(user.getBirthDate());
        dto.setGender(user.getGender());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setCountry(user.getCountry());
        dto.setPostalCode(user.getPostalCode());
        dto.setBio(user.getBio());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setMembershipLevel(user.getMembershipLevel());
        dto.setPoints(user.getPoints());
        dto.setRegistrationDate(user.getRegistrationDate());
        dto.setIsActive(user.getIsActive());
        
        return dto;
    }
    
    /**
     * Update existing domain model with DTO data
     */
    public void updateDomainModel(User user, UserDTO dto) {
        if (user == null || dto == null) {
            return;
        }
        
        if (dto.getMemberId() != null) {
            user.setMemberId(dto.getMemberId());
        }
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getBirthDate() != null) {
            user.setBirthDate(dto.getBirthDate());
        }
        if (dto.getGender() != null) {
            user.setGender(dto.getGender());
        }
        if (dto.getAddress() != null) {
            user.setAddress(dto.getAddress());
        }
        if (dto.getCity() != null) {
            user.setCity(dto.getCity());
        }
        if (dto.getCountry() != null) {
            user.setCountry(dto.getCountry());
        }
        if (dto.getPostalCode() != null) {
            user.setPostalCode(dto.getPostalCode());
        }
        if (dto.getBio() != null) {
            user.setBio(dto.getBio());
        }
        if (dto.getAvatarUrl() != null) {
            user.setAvatarUrl(dto.getAvatarUrl());
        }
        if (dto.getMembershipLevel() != null) {
            user.setMembershipLevel(dto.getMembershipLevel());
        }
        if (dto.getPoints() != null) {
            user.setPoints(dto.getPoints());
        }
        if (dto.getRegistrationDate() != null) {
            user.setRegistrationDate(dto.getRegistrationDate());
        }
        if (dto.getIsActive() != null) {
            user.setIsActive(dto.getIsActive());
        }
    }
}
