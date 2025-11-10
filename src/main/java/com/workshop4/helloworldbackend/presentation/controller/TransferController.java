package com.workshop4.helloworldbackend.presentation.controller;

import com.workshop4.helloworldbackend.application.dto.TransferCreateRequestDTO;
import com.workshop4.helloworldbackend.application.dto.TransferListResponseDTO;
import com.workshop4.helloworldbackend.application.dto.TransferResponseDTO;
import com.workshop4.helloworldbackend.application.mapper.TransferMapper;
import com.workshop4.helloworldbackend.domain.model.Transfer;
import com.workshop4.helloworldbackend.domain.usecase.TransferUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Transfer Controller
 * REST API endpoints for points transfer operations
 * Implements OpenAPI specification from transfer.yml
 */
@RestController
@RequestMapping("/transfers")
@Validated
public class TransferController {
    
    private final TransferUseCase transferUseCase;
    private final TransferMapper transferMapper;
    
    public TransferController(TransferUseCase transferUseCase, TransferMapper transferMapper) {
        this.transferUseCase = transferUseCase;
        this.transferMapper = transferMapper;
    }
    
    /**
     * POST /transfers
     * Creates a new points transfer between users
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTransfer(@Valid @RequestBody TransferCreateRequestDTO request) {
        try {
            // Create the transfer
            Transfer transfer = transferUseCase.createTransfer(
                request.getFromUserId(),
                request.getToUserId(),
                request.getAmount(),
                request.getNote()
            );
            
            // Convert to response DTO
            TransferResponseDTO responseDTO = transferMapper.toResponseDTO(transfer);
            
            // Build response with transfer wrapped in an object
            Map<String, Object> response = new HashMap<>();
            response.put("transfer", responseDTO);
            
            // Add Idempotency-Key header
            HttpHeaders headers = new HttpHeaders();
            headers.add("Idempotency-Key", transfer.getIdemKey());
            
            return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
            
        } catch (IllegalArgumentException e) {
            // Validation errors (400 Bad Request)
            return ResponseEntity.badRequest().body(createErrorResponse("VALIDATION_ERROR", e.getMessage()));
            
        } catch (IllegalStateException e) {
            // Business logic errors (409 Conflict for insufficient points, inactive users)
            if (e.getMessage().contains("Insufficient points") || 
                e.getMessage().contains("not active")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(createErrorResponse("BUSINESS_RULE_VIOLATION", e.getMessage()));
            }
            // 422 Unprocessable Entity for other state issues
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(createErrorResponse("UNPROCESSABLE", e.getMessage()));
                
        } catch (Exception e) {
            // Unexpected errors (500 Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse("INTERNAL_ERROR", "An unexpected error occurred"));
        }
    }
    
    /**
     * GET /transfers/{id}
     * Gets a transfer by its idempotency key
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTransferById(@PathVariable String id) {
        return transferUseCase.getTransferByIdemKey(id)
            .map(transfer -> {
                TransferResponseDTO responseDTO = transferMapper.toResponseDTO(transfer);
                Map<String, Object> response = new HashMap<>();
                response.put("transfer", responseDTO);
                return ResponseEntity.ok(response);
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createErrorResponse("NOT_FOUND", "Transfer not found with id: " + id)));
    }
    
    /**
     * GET /transfers?userId={userId}&page={page}&pageSize={pageSize}
     * Lists all transfers involving a specific user
     */
    @GetMapping
    public ResponseEntity<?> getTransfersByUserId(
            @RequestParam @Min(1) Long userId,
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(200) int pageSize
    ) {
        try {
            // Get transfers from use case
            TransferUseCase.TransferListResult result = transferUseCase.getTransfersByUserId(userId, page, pageSize);
            
            // Convert to response DTOs
            List<TransferResponseDTO> transferDTOs = result.getData().stream()
                .map(transferMapper::toResponseDTO)
                .collect(Collectors.toList());
            
            // Build response
            TransferListResponseDTO response = new TransferListResponseDTO(
                transferDTOs,
                result.getPage(),
                result.getPageSize(),
                result.getTotal()
            );
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(createErrorResponse("VALIDATION_ERROR", e.getMessage()));
        }
    }
    
    /**
     * Helper method to create error response
     */
    private Map<String, Object> createErrorResponse(String error, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", error);
        errorResponse.put("message", message);
        return errorResponse;
    }
}
