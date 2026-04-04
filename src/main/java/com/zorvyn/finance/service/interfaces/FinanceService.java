package com.zorvyn.finance.service.interfaces;

import com.zorvyn.finance.dto.request.RecordRequest;
import com.zorvyn.finance.dto.response.RecordResponse;

public interface FinanceService {

    RecordResponse createRecord(RecordRequest request);
}