package com.zorvyn.finance.dto.response;

import com.zorvyn.finance.enums.Category;
import com.zorvyn.finance.enums.RecordType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class RecordResponse {

    private Long id;
    private Double amount;
    private RecordType type;
    private Category category;
    private LocalDate date;
    private String notes;
}