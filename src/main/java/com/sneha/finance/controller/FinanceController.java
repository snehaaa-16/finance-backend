package com.sneha.finance.controller;

import com.sneha.finance.dto.request.RecordRequest;
import com.sneha.finance.dto.response.ApiResponse;
import com.sneha.finance.dto.response.RecordResponse;
import com.sneha.finance.dto.response.SummaryResponse;
import com.sneha.finance.enums.Category;
import com.sneha.finance.enums.RecordType;
import com.sneha.finance.service.interfaces.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    @PostMapping
    public ApiResponse<RecordResponse> create(@RequestBody RecordRequest request) {
        return ApiResponse.<RecordResponse>builder()
                .success(true)
                .data(financeService.createRecord(request))
                .message("Record created successfully")
                .build();
    }

    @GetMapping
    public ApiResponse<Page<RecordResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "date") String sortBy
    ) {
        return ApiResponse.<Page<RecordResponse>>builder()
                .success(true)
                .data(financeService.getRecords(page, size, sortBy))
                .message("Records fetched successfully")
                .build();
    }

    @GetMapping("/filter")
    public ApiResponse<Page<RecordResponse>> filter(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "date") String sortBy
    ) {
        return ApiResponse.<Page<RecordResponse>>builder()
                .success(true)
                .data(financeService.getRecordsWithFilters(
                        type, category, startDate, endDate, page, size, sortBy))
                .message("Filtered records fetched successfully")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<RecordResponse> update(
            @PathVariable Long id,
            @RequestBody RecordRequest request
    ) {
        return ApiResponse.<RecordResponse>builder()
                .success(true)
                .data(financeService.updateRecord(id, request))
                .message("Record updated successfully")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        financeService.deleteRecord(id);
        return ApiResponse.<String>builder()
                .success(true)
                .data(null)
                .message("Record deleted successfully")
                .build();
    }

    @GetMapping("/summary")
    public ApiResponse<SummaryResponse> getSummary() {
        return ApiResponse.<SummaryResponse>builder()
                .success(true)
                .data(financeService.getSummary())
                .message("Summary fetched successfully")
                .build();
    }
}