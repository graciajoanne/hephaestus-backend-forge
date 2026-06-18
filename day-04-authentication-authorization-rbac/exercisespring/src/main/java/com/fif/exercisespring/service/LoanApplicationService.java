package com.fif.exercisespring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fif.exercisespring.dto.CreateLoanApplicationRequest;
import com.fif.exercisespring.dto.LoanApplicationResponse;
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

    public List<LoanApplicationResponse> getAllLoanApplications() {
        List<LoanApplicationResponse> responses = new ArrayList<>();
        for (LoanApplication loanApplication : loanApplications.values()) {
            responses.add(buildResponse(loanApplication));
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

    public LoanApplicationResponse approveLoanApplication(Long id) {
        LoanApplication loanApplication = loanApplications.get(id);
        if (loanApplication == null) {
            throw new LoanApplicationNotFoundException(id);
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