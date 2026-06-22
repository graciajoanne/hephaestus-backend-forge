package com.fif.exercise2.controller;

import com.fif.exercise2.dto.ApiResponse;
import com.fif.exercise2.dto.CreateLoanApplicationRequest;
import com.fif.exercise2.dto.LoanApplicationResponse;
import com.fif.exercise2.dto.LoanSummaryResponse;
import com.fif.exercise2.dto.UpdateLoanStatusRequest;
import com.fif.exercise2.service.LoanApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping("/loan-applications")
    public ResponseEntity<ApiResponse<LoanApplicationResponse>> createLoanApplication(
            @Valid @RequestBody CreateLoanApplicationRequest request) {
        LoanApplicationResponse data = loanApplicationService.createLoanApplication(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Loan application created successfully", data));
    }

    @GetMapping("/loan-applications/{id}")
    public ResponseEntity<ApiResponse<LoanApplicationResponse>> getLoanApplicationById(
            @PathVariable Long id) {
        LoanApplicationResponse data = loanApplicationService.getLoanApplicationById(id);
        return ResponseEntity.ok(ApiResponse.success("Loan application retrieved successfully", data));
    }

    @GetMapping("/loan-applications")
    public ResponseEntity<ApiResponse<List<LoanApplicationResponse>>> getAllLoanApplications(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) ZonedDateTime start,
            @RequestParam(required = false) ZonedDateTime end) {
        List<LoanApplicationResponse> data;
        if (date != null) {
            data = loanApplicationService.getLoansByDate(date);
        } else if (start != null && end != null) {
            data = loanApplicationService.getLoansByDateRange(start, end);
        } else if (status != null) {
            data = loanApplicationService.getLoansByStatus(status);
        } else {
            data = loanApplicationService.getAllLoanApplications();
        }

        return ResponseEntity.ok(ApiResponse.success("Loan applications retrieved successfully", data));
    }

    @GetMapping("/customers/{customerId}/loan-applications")
    public ResponseEntity<ApiResponse<List<LoanApplicationResponse>>> getLoansByCustomerId(
            @PathVariable Long customerId) {
        List<LoanApplicationResponse> data = loanApplicationService.getLoansByCustomerId(customerId);
        return ResponseEntity.ok(ApiResponse.success("Loan applications retrieved successfully", data));
    }

    @PatchMapping("/loan-applications/{id}/status")
    public ResponseEntity<ApiResponse<LoanApplicationResponse>> updateLoanStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateLoanStatusRequest request) {
        LoanApplicationResponse data = loanApplicationService.updateLoanStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success("Loan status updated successfully", data));
    }

    @Operation(summary = "Get All Loan Applications Paged")
    @GetMapping("/loan-applications/paged")
    public ResponseEntity<ApiResponse<Page<LoanApplicationResponse>>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status) {
        Page<LoanApplicationResponse> data = loanApplicationService
            .getAllLoanApplicationsPaged(page, size, status);
        return ResponseEntity.ok(ApiResponse.success("Loan applications retrieved successfully", data));
    }

    @Operation(summary = "Get Loan Summary by Status")
    @GetMapping("/loan-applications/summary")
    public ResponseEntity<ApiResponse<List<LoanSummaryResponse>>> getLoanSummary() {
        List<LoanSummaryResponse> data = loanApplicationService.getLoanSummaryByStatus();
        return ResponseEntity.ok(ApiResponse.success("Loan summary retrieved successfully", data));
    }
}