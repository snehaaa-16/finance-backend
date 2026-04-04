package com.zorvyn.finance.service.interfaces;

import com.zorvyn.finance.dto.request.RecordRequest;
import com.zorvyn.finance.dto.response.RecordResponse;
import org.springframework.data.domain.Page;

public interface FinanceService {

    RecordResponse createRecord(RecordRequest request);
    Page<RecordResponse> getRecords(int page, int size, String sortBy);
}