package com.sneha.finance.dto.request;

import com.sneha.finance.enums.Category;
import com.sneha.finance.enums.RecordType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RecordRequest {

    private Double amount;
    private RecordType type;
    private Category category;
    private LocalDate date;
    private String notes;
}