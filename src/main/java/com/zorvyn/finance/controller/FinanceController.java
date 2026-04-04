package com.zorvyn.finance.controller;

import com.zorvyn.finance.dto.request.RecordRequest;
import com.zorvyn.finance.dto.response.RecordResponse;
import com.zorvyn.finance.service.interfaces.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    @PostMapping
    public RecordResponse create(@RequestBody RecordRequest request) {
        return financeService.createRecord(request);
    }
}