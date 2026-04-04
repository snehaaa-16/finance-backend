package com.zorvyn.finance.service.impl;

import com.zorvyn.finance.dto.request.RecordRequest;
import com.zorvyn.finance.dto.response.RecordResponse;
import com.zorvyn.finance.entity.FinancialRecord;
import com.zorvyn.finance.repository.FinancialRecordRepository;
import com.zorvyn.finance.service.interfaces.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}