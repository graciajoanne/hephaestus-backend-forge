package com.fif.exercisespring.controller;

import com.fif.exercisespring.dto.CreateLoanApplicationRequest;
import com.fif.exercisespring.dto.LoanApplicationResponse;
import com.fif.exercisespring.security.AuthUtil;
import com.fif.exercisespring.security.RoleValidator;
import com.fif.exercisespring.service.AuthService;
import com.fif.exercisespring.service.LoanApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan-applications")
@AllArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<LoanApplicationResponse> createLoanApplication(
            @RequestHeader("Authorization") String authorization,
            @RequestBody CreateLoanApplicationRequest request) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role, "ADMIN", "STAFF");
        LoanApplicationResponse response = loanApplicationService.createLoanApplication(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationResponse>> getAllLoanApplications(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long customer_id) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role, "ADMIN", "STAFF", "APPROVER","MANAGER");
        return ResponseEntity.ok(loanApplicationService.getAllLoanApplications(status, customer_id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationResponse> getLoanApplication(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role, "ADMIN", "STAFF", "APPROVER", "MANAGER");
        return ResponseEntity.ok(loanApplicationService.getLoanApplication(id));
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<LoanApplicationResponse> approveLoanApplication(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role, "ADMIN", "APPROVER", "MANAGER");
        return ResponseEntity.ok(loanApplicationService.approveLoanApplication(id,role));
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<LoanApplicationResponse> rejectLoanApplication(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role, "ADMIN", "APPROVER", "MANAGER");
        return ResponseEntity.ok(loanApplicationService.rejectLoanApplication(id));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<LoanApplicationResponse> cancelLoanApplication(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role, "ADMIN", "APPROVER");
        return ResponseEntity.ok(loanApplicationService.cancelLoanApplication(id));
    }
}