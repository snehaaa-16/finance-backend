package com.sneha.finance.controller;

import com.sneha.finance.dto.request.RecordRequest;
import com.sneha.finance.dto.response.RecordResponse;
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
    public RecordResponse create(@RequestBody RecordRequest request) {
        return financeService.createRecord(request);
    }

    @GetMapping
    public Page<RecordResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "date") String sortBy
    ) {
        return financeService.getRecords(page, size, sortBy);
    }

    @GetMapping("/filter")
    public Page<RecordResponse> filter(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "date") String sortBy
    ) {
        System.out.println("API HIT");
        return financeService.getRecordsWithFilters(
                type, category, startDate, endDate, page, size, sortBy
        );
    }

    @PutMapping("/{id}")
    public RecordResponse update(
            @PathVariable Long id,
            @RequestBody RecordRequest request
    ) {
        return financeService.updateRecord(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        financeService.deleteRecord(id);
        return "Record deleted successfully";
    }
}