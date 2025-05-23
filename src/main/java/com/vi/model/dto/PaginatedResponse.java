package com.vi.model.dto;

import java.util.List;

public class PaginatedResponse<T> {
    private List<T> data;
    private long totalCount;
 
    public PaginatedResponse(List<T> data, long totalCount) {
        this.data = data;
        this.totalCount = totalCount;
    }
 
    // Getters and setters
    public List<T> getData() {
        return data;
    }
 
    public void setData(List<T> data) {
        this.data = data;
    }
 
    public long getTotalCount() {
        return totalCount;
    }
 
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}