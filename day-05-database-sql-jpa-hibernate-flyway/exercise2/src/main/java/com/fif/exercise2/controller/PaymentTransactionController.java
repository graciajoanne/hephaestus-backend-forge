package com.fif.exercise2.controller;

import com.fif.exercise2.dto.ApiResponse;
import com.fif.exercise2.dto.CreatePaymentTransactionRequest;
import com.fif.exercise2.dto.PaymentTransactionResponse;
import com.fif.exercise2.service.PaymentTransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PaymentTransactionController {

    private final PaymentTransactionService paymentTransactionService;

    @PostMapping("/payment-transactions")
    public ResponseEntity<ApiResponse<PaymentTransactionResponse>> createPaymentTransaction(
            @Valid @RequestBody CreatePaymentTransactionRequest request) {
        PaymentTransactionResponse data = paymentTransactionService
            .createPaymentTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Payment transaction created successfully", data));
    }

    @GetMapping("/repayment-schedules/{repaymentScheduleId}/payment-transactions")
    public ResponseEntity<ApiResponse<List<PaymentTransactionResponse>>> getByRepaymentScheduleId(
            @PathVariable Long repaymentScheduleId) {
        List<PaymentTransactionResponse> data = paymentTransactionService
            .getByRepaymentScheduleId(repaymentScheduleId);
        return ResponseEntity.ok(ApiResponse.success("Payment transactions retrieved successfully", data));
    }
}