package com.fif.exercise2.exception;

public class LoanApplicationNotFoundException extends RuntimeException {
    public LoanApplicationNotFoundException(Long id) {
        super("Loan application not found with id: " + id);
    }
}