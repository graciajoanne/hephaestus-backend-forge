package com.fif.exercise2.repository;

import com.fif.exercise2.entity.LoanApplicationEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface LoanApplicationRepository extends JpaRepository<LoanApplicationEntity, Long> {

    List<LoanApplicationEntity> findByCustomerId(Long customerId);

    List<LoanApplicationEntity> findByStatus(String status);

    @Query("SELECT l FROM LoanApplicationEntity l JOIN FETCH l.customer WHERE l.id = :id")
    Optional<LoanApplicationEntity> findByIdWithCustomer(@Param("id") Long id);

    @Query("SELECT l FROM LoanApplicationEntity l JOIN l.customer c WHERE c.id = :customerId")
    List<LoanApplicationEntity> findLoansByCustomerId(@Param("customerId") Long customerId);

    Page<LoanApplicationEntity> findByStatus(String status, Pageable pageable);

    @Query("SELECT l FROM LoanApplicationEntity l WHERE DATE(l.createdAt) = :date")
    List<LoanApplicationEntity> findByCreatedAtDate(@Param("date") LocalDate date);

    @Query("SELECT l FROM LoanApplicationEntity l WHERE l.createdAt BETWEEN :start AND :end")
    List<LoanApplicationEntity> findByCreatedAtBetween(
        @Param("start") ZonedDateTime start,
        @Param("end") ZonedDateTime end);
    
    @Query(value = """
        SELECT status, COUNT(*) AS total_loan, SUM(loan_amount) AS total_amount
        FROM loan_applications
        GROUP BY status
        """, nativeQuery = true)
    List<Object[]> getSummaryByStatus();
}