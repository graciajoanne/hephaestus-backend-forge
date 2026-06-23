package com.fif.exercisespring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fif.exercisespring.dto.CreateLoanApplicationRequest;
import com.fif.exercisespring.dto.LoanApplicationResponse;
import com.fif.exercisespring.exception.ForbiddenException;
import com.fif.exercisespring.exception.LoanApplicationNotFoundException;
import com.fif.exercisespring.model.LoanApplication;

@Service
public class LoanApplicationService {

    private final Map<Long, LoanApplication> loanApplications = new HashMap<>();

    private Long sequence = 1L;

    public LoanApplicationResponse createLoanApplication(CreateLoanApplicationRequest request) {
        LoanApplication loanApplication = new LoanApplication(
            sequence,
            request.getCustomerId(),
            request.getLoanAmount(),
            request.getTenorMonth(),
            request.getPurpose(),
            "SUBMITTED");
        loanApplications.put(sequence, loanApplication);
        sequence++;
        return buildResponse(loanApplication);
    }

    public List<LoanApplicationResponse> getAllLoanApplications(String status, Long id) {
        List<LoanApplicationResponse> responses = new ArrayList<>();

        for (LoanApplication loan : loanApplications.values()) {
            boolean match = true;
            if (status != null && !status.isEmpty()) {
                match = loan.getStatus().toString().equalsIgnoreCase(status);
            }
            if(match && id != null) {
                match =loan.getCustomerId().equals(id);
            }
            if (match) {
                responses.add(buildResponse(loan));
            }
        }
        return responses;
    }

    public LoanApplicationResponse getLoanApplication(Long id) {
        LoanApplication loanApplication = loanApplications.get(id);
        if (loanApplication == null) {
            throw new LoanApplicationNotFoundException(id);
        }
        return buildResponse(loanApplication);
    }

    private static final Double MANAGER_MIN_AMOUNT = 10_000_000.0;
    public LoanApplicationResponse approveLoanApplication(Long id, String role) {
        LoanApplication loanApplication = loanApplications.get(id);
        if (loanApplication == null) {
            throw new LoanApplicationNotFoundException(id);
        }
        if (role.equals("MANAGER") && loanApplication.getLoanAmount() <= MANAGER_MIN_AMOUNT) {
            throw new ForbiddenException("Manager can only approve loan above " + MANAGER_MIN_AMOUNT);
        }
        loanApplication.setStatus("APPROVED");
        return buildResponse(loanApplication);
    }

    public LoanApplicationResponse rejectLoanApplication(Long id) {
        LoanApplication loanApplication = loanApplications.get(id);
        if (loanApplication == null) {
            throw new LoanApplicationNotFoundException(id);
        }
        loanApplication.setStatus("REJECTED");
        return buildResponse(loanApplication);
    }

    public LoanApplicationResponse cancelLoanApplication(Long id) {
        LoanApplication loanApplication = loanApplications.get(id);
        if (loanApplication == null) {
            throw new LoanApplicationNotFoundException(id);
        }
        loanApplication.setStatus("CANCEL");
        return buildResponse(loanApplication);
    }

    private LoanApplicationResponse buildResponse(LoanApplication loanApplication) {
        return new LoanApplicationResponse(
                loanApplication.getId(),
                loanApplication.getCustomerId(),
                loanApplication.getLoanAmount(),
                loanApplication.getTenorMonth(),
                loanApplication.getPurpose(),
                loanApplication.getStatus()
        );
    }
}