package com.workshop4.helloworldbackend.presentation.controller;

import com.workshop4.helloworldbackend.application.dto.UserDTO;
import com.workshop4.helloworldbackend.application.dto.UserResponseDTO;
import com.workshop4.helloworldbackend.application.mapper.UserMapper;
import com.workshop4.helloworldbackend.domain.model.User;
import com.workshop4.helloworldbackend.domain.usecase.UserUseCase;
import com.workshop4.helloworldbackend.domain.usecase.UserUseCase.UserStats;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST Controller - Presentation layer
 * Handles HTTP requests and responses using DTOs
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    private final UserUseCase userUseCase;
    private final UserMapper userMapper;
    
    public UserController(UserUseCase userUseCase, UserMapper userMapper) {
        this.userUseCase = userUseCase;
        this.userMapper = userMapper;
    }
    
    // GET all users
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(required = false) Boolean activeOnly,
            @RequestParam(required = false) String search) {
        
        List<User> users;
        
        if (search != null && !search.trim().isEmpty()) {
            users = userUseCase.searchUsers(search);
        } else if (Boolean.TRUE.equals(activeOnly)) {
            users = userUseCase.getActiveUsers();
        } else {
            users = userUseCase.getAllUsers();
        }
        
        List<UserResponseDTO> userDTOs = users.stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
        
        Map<String, Object> response = new HashMap<>();
        response.put("count", userDTOs.size());
        response.put("message", "Users retrieved successfully");
        response.put("users", userDTOs);
        
        return ResponseEntity.ok(response);
    }
    
    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        User user = userUseCase.getUserById(id);
        UserResponseDTO userDTO = userMapper.toResponseDTO(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User retrieved successfully");
        response.put("user", userDTO);
        
        return ResponseEntity.ok(response);
    }
    
    // GET user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@PathVariable String email) {
        User user = userUseCase.getUserByEmail(email);
        UserResponseDTO userDTO = userMapper.toResponseDTO(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User retrieved successfully");
        response.put("user", userDTO);
        
        return ResponseEntity.ok(response);
    }
    
    // Search users with criteria
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Boolean isActive) {
        
        List<User> users = userUseCase.searchUsersByCriteria(
                firstName, lastName, email, city, isActive);
        
        List<UserResponseDTO> userDTOs = users.stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
        
        Map<String, Object> response = new HashMap<>();
        response.put("count", userDTOs.size());
        response.put("message", "Search completed successfully");
        response.put("users", userDTOs);
        
        return ResponseEntity.ok(response);
    }
    
    // GET user statistics
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        UserStats stats = userUseCase.getUserStats();
        
        Map<String, Object> statsMap = new HashMap<>();
        statsMap.put("totalUsers", stats.getTotalUsers());
        statsMap.put("activeUsers", stats.getActiveUsers());
        statsMap.put("inactiveUsers", stats.getInactiveUsers());
        
        Map<String, Object> response = new HashMap<>();
        response.put("stats", statsMap);
        response.put("message", "User statistics retrieved successfully");
        
        return ResponseEntity.ok(response);
    }
    
    // POST create user
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userMapper.toDomainModel(userDTO);
        User createdUser = userUseCase.createUser(user);
        UserResponseDTO responseDTO = userMapper.toResponseDTO(createdUser);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully");
        response.put("user", responseDTO);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    // PUT update user (full update)
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO) {
        
        User user = userMapper.toDomainModel(userDTO);
        User updatedUser = userUseCase.updateUser(id, user);
        UserResponseDTO responseDTO = userMapper.toResponseDTO(updatedUser);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User updated successfully");
        response.put("user", responseDTO);
        
        return ResponseEntity.ok(response);
    }
    
    // PATCH partial update
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> partialUpdateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {
        
        User user = userMapper.toDomainModel(userDTO);
        User updatedUser = userUseCase.partialUpdateUser(id, user);
        UserResponseDTO responseDTO = userMapper.toResponseDTO(updatedUser);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User updated successfully");
        response.put("user", responseDTO);
        
        return ResponseEntity.ok(response);
    }
    
    // DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        userUseCase.deleteUser(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        response.put("deletedUserId", id);
        
        return ResponseEntity.ok(response);
    }
    
    // POST activate user
    @PostMapping("/{id}/activate")
    public ResponseEntity<Map<String, Object>> activateUser(@PathVariable Long id) {
        User user = userUseCase.activateUser(id);
        UserResponseDTO responseDTO = userMapper.toResponseDTO(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User activated successfully");
        response.put("user", responseDTO);
        
        return ResponseEntity.ok(response);
    }
    
    // POST deactivate user
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Map<String, Object>> deactivateUser(@PathVariable Long id) {
        User user = userUseCase.deactivateUser(id);
        UserResponseDTO responseDTO = userMapper.toResponseDTO(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deactivated successfully");
        response.put("user", responseDTO);
        
        return ResponseEntity.ok(response);
    }
}
