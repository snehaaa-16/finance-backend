package com.sneha.finance.service.interfaces;

import com.sneha.finance.dto.request.RecordRequest;
import com.sneha.finance.dto.response.RecordResponse;
import com.sneha.finance.enums.Category;
import com.sneha.finance.enums.RecordType;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface FinanceService {

    RecordResponse createRecord(RecordRequest request);
    Page<RecordResponse> getRecords(int page, int size, String sortBy);
    Page<RecordResponse> getRecordsWithFilters(
            RecordType type,
            Category category,
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size,
            String sortBy
    );
    RecordResponse updateRecord(Long id, RecordRequest request);
    void deleteRecord(Long id);
}