package com.zorvyn.finance.dto.request;

import com.zorvyn.finance.enums.Category;
import com.zorvyn.finance.enums.RecordType;
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