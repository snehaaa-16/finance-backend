package com.zorvyn.finance.repository;

import com.zorvyn.finance.entity.FinancialRecord;
import com.zorvyn.finance.enums.RecordType;
import com.zorvyn.finance.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    @Query("SELECT r FROM FinancialRecord r WHERE r.deletedAt IS NULL")
    Page<FinancialRecord> findAllActive(Pageable pageable);

    @Query("""
    SELECT r FROM FinancialRecord r
    WHERE r.deletedAt IS NULL
    AND (:type IS NULL OR r.type = :type)
    AND (:category IS NULL OR r.category = :category)
    AND r.date >= :startDate
    AND r.date <= :endDate
    """)
    Page<FinancialRecord> findWithFilters(
            @Param("type") RecordType type,
            @Param("category") Category category,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );
}