package com.workshop4.helloworldbackend.controller;

import com.workshop4.helloworldbackend.entity.User;
import com.workshop4.helloworldbackend.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * GET /users - Get all users
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(required = false) Boolean activeOnly,
            @RequestParam(required = false) String search) {
        
        logger.info("GET /api/users - activeOnly: {}, search: {}", activeOnly, search);
        
        List<User> users;
        
        if (search != null && !search.trim().isEmpty()) {
            users = userService.searchUsersByName(search.trim());
        } else if (Boolean.TRUE.equals(activeOnly)) {
            users = userService.getAllActiveUsers();
        } else {
            users = userService.getAllUsers();
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("count", users.size());
        response.put("message", "Users retrieved successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /users/{id} - Get user by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        logger.info("GET /api/users/{}", id);
        
        User user = userService.getUserById(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("message", "User retrieved successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * POST /users - Create new user
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody User user) {
        logger.info("POST /api/users - Creating user with email: {}", user.getEmail());
        
        User createdUser = userService.createUser(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("user", createdUser);
        response.put("message", "User created successfully");
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * PUT /users/{id} - Update user (full update)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable Long id, 
            @Valid @RequestBody User userDetails) {
        
        logger.info("PUT /api/users/{} - Updating user", id);
        
        User updatedUser = userService.updateUser(id, userDetails);
        
        Map<String, Object> response = new HashMap<>();
        response.put("user", updatedUser);
        response.put("message", "User updated successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * PATCH /users/{id} - Partial update user
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> partialUpdateUser(
            @PathVariable Long id, 
            @RequestBody User userDetails) {
        
        logger.info("PATCH /api/users/{} - Partial updating user", id);
        
        User updatedUser = userService.partialUpdateUser(id, userDetails);
        
        Map<String, Object> response = new HashMap<>();
        response.put("user", updatedUser);
        response.put("message", "User updated successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /users/{id} - Delete user (hard delete)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        logger.info("DELETE /api/users/{} - Deleting user", id);
        
        userService.deleteUser(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        response.put("deletedUserId", id);
        
        return ResponseEntity.ok(response);
    }

    /**
     * POST /users/{id}/deactivate - Deactivate user (soft delete)
     */
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Map<String, Object>> deactivateUser(@PathVariable Long id) {
        logger.info("POST /api/users/{}/deactivate - Deactivating user", id);
        
        User deactivatedUser = userService.deactivateUser(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("user", deactivatedUser);
        response.put("message", "User deactivated successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * POST /users/{id}/activate - Activate user
     */
    @PostMapping("/{id}/activate")
    public ResponseEntity<Map<String, Object>> activateUser(@PathVariable Long id) {
        logger.info("POST /api/users/{}/activate - Activating user", id);
        
        User activatedUser = userService.activateUser(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("user", activatedUser);
        response.put("message", "User activated successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /users/search - Advanced search users
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Boolean isActive) {
        
        logger.info("GET /api/users/search - firstName: {}, lastName: {}, email: {}, city: {}, isActive: {}", 
                   firstName, lastName, email, city, isActive);
        
        List<User> users = userService.searchUsers(firstName, lastName, email, city, isActive);
        
        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("count", users.size());
        response.put("message", "Search completed successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * GET /users/email/{email} - Get user by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@PathVariable String email) {
        logger.info("GET /api/users/email/{}", email);
        
        return userService.getUserByEmail(email)
                .map(user -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("user", user);
                    response.put("message", "User retrieved successfully");
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "User not found with email: " + email);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * GET /users/stats - Get user statistics
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        logger.info("GET /api/users/stats - Getting user statistics");
        
        List<User> allUsers = userService.getAllUsers();
        List<User> activeUsers = userService.getAllActiveUsers();
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", allUsers.size());
        stats.put("activeUsers", activeUsers.size());
        stats.put("inactiveUsers", allUsers.size() - activeUsers.size());
        
        Map<String, Object> response = new HashMap<>();
        response.put("stats", stats);
        response.put("message", "User statistics retrieved successfully");
        
        return ResponseEntity.ok(response);
    }
}