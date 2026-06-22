package com.fif.exercise2.controller;

import com.fif.exercise2.dto.ApiResponse;
import com.fif.exercise2.dto.RepaymentScheduleResponse;
import com.fif.exercise2.service.RepaymentScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RepaymentScheduleController {

    private final RepaymentScheduleService repaymentScheduleService;

    @GetMapping("/loan-applications/{loanApplicationId}/repayment-schedules")
    public ResponseEntity<ApiResponse<List<RepaymentScheduleResponse>>> getByLoanApplicationId(
            @PathVariable Long loanApplicationId,
            @RequestParam(required = false) String status) {
        List<RepaymentScheduleResponse> data = repaymentScheduleService.getByLoanApplicationId(loanApplicationId, status);
        return ResponseEntity.ok(ApiResponse.success("Repayment schedules retrieved successfully", data));
    }

    @GetMapping("/repayment-schedules/{id}")
    public ResponseEntity<ApiResponse<RepaymentScheduleResponse>> getById(
            @PathVariable Long id) {
        RepaymentScheduleResponse data = repaymentScheduleService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Repayment schedule retrieved successfully", data));
    }
}