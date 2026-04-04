package com.zorvyn.finance.controller;

import com.zorvyn.finance.dto.request.RecordRequest;
import com.zorvyn.finance.dto.response.RecordResponse;
import com.zorvyn.finance.enums.Category;
import com.zorvyn.finance.enums.RecordType;
import com.zorvyn.finance.service.interfaces.FinanceService;
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
}