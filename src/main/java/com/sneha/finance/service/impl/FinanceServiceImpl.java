package com.sneha.finance.service.impl;

import com.sneha.finance.dto.request.RecordRequest;
import com.sneha.finance.dto.response.RecordResponse;
import com.sneha.finance.entity.FinancialRecord;
import com.sneha.finance.enums.Category;
import com.sneha.finance.enums.RecordType;
import com.sneha.finance.repository.FinancialRecordRepository;
import com.sneha.finance.service.interfaces.FinanceService;
import com.sneha.finance.dto.response.SummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private final FinancialRecordRepository recordRepository;

    @Override
    public RecordResponse createRecord(RecordRequest request) {

        FinancialRecord record = FinancialRecord.builder()
                .amount(java.math.BigDecimal.valueOf(request.getAmount()))
                .type(request.getType())
                .category(request.getCategory())
                .date(request.getDate())
                .notes(request.getNotes())
                .build();

        recordRepository.save(record);

        return RecordResponse.builder()
                .id(record.getId())
                .amount(record.getAmount().doubleValue())
                .type(record.getType())
                .category(record.getCategory())
                .date(record.getDate())
                .notes(record.getNotes())
                .build();
    }

    @Override
    public Page<RecordResponse> getRecords(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).descending()
        );

        Page<FinancialRecord> recordPage = recordRepository.findAllActive(pageable);

        return recordPage.map(record -> RecordResponse.builder()
                .id(record.getId())
                .amount(record.getAmount().doubleValue())
                .type(record.getType())
                .category(record.getCategory())
                .date(record.getDate())
                .notes(record.getNotes())
                .build());
    }

    @Override
    public Page<RecordResponse> getRecordsWithFilters(
            RecordType type,
            Category category,
            LocalDate startDate,
            LocalDate endDate,
            int page,
            int size,
            String sortBy
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy).descending()
        );

        if (startDate == null) {
            startDate = LocalDate.of(1970, 1, 1);
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }

        Page<FinancialRecord> recordPage =
                recordRepository.findWithFilters(type, category, startDate, endDate, pageable);

        return recordPage.map(record -> RecordResponse.builder()
                .id(record.getId())
                .amount(record.getAmount().doubleValue())
                .type(record.getType())
                .category(record.getCategory())
                .date(record.getDate())
                .notes(record.getNotes())
                .build());
    }

    @Override
    public RecordResponse updateRecord(Long id, RecordRequest request) {

        FinancialRecord record = recordRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null)
                .orElseThrow(() -> new RuntimeException("Record not found or deleted"));

        record.setAmount(java.math.BigDecimal.valueOf(request.getAmount()));
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setNotes(request.getNotes());

        recordRepository.save(record);

        return RecordResponse.builder()
                .id(record.getId())
                .amount(record.getAmount().doubleValue())
                .type(record.getType())
                .category(record.getCategory())
                .date(record.getDate())
                .notes(record.getNotes())
                .build();
    }

    @Override
    public void deleteRecord(Long id) {

        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        if (record.getDeletedAt() != null) {
            throw new RuntimeException("Record already deleted");
        }

        record.setDeletedAt(LocalDateTime.now());
        recordRepository.save(record);
    }

    @Override
    public SummaryResponse getSummary() {

        List<FinancialRecord> records = recordRepository.findAllActiveList();

        double income = records.stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .mapToDouble(r -> r.getAmount().doubleValue())
                .sum();

        double expense = records.stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .mapToDouble(r -> r.getAmount().doubleValue())
                .sum();

        return SummaryResponse.builder()
                .totalIncome(income)
                .totalExpense(expense)
                .balance(income - expense)
                .build();
    }
}