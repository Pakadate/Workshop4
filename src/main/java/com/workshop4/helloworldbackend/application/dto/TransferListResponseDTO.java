package com.workshop4.helloworldbackend.application.dto;

import java.util.List;

/**
 * Transfer List Response DTO
 * Used for returning paginated list of transfers
 */
public class TransferListResponseDTO {
    
    private List<TransferResponseDTO> data;
    private int page;
    private int pageSize;
    private long total;
    
    // Constructors
    public TransferListResponseDTO() {
    }
    
    public TransferListResponseDTO(List<TransferResponseDTO> data, int page, int pageSize, long total) {
        this.data = data;
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }
    
    // Getters and Setters
    public List<TransferResponseDTO> getData() {
        return data;
    }
    
    public void setData(List<TransferResponseDTO> data) {
        this.data = data;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public long getTotal() {
        return total;
    }
    
    public void setTotal(long total) {
        this.total = total;
    }
}
