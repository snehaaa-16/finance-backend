package com.zorvyn.finance.repository;

import com.zorvyn.finance.entity.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
}