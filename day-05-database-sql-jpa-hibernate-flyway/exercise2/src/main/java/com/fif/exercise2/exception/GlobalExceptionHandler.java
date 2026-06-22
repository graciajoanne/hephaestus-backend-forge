package com.fif.exercise2.exception;

import com.fif.exercise2.dto.ErrorResponse;
import com.fif.exercise2.dto.FieldErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
            false,
            "CUSTOMER_NOT_FOUND",
            ex.getMessage(),
            new ArrayList<>()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(LoanApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFound(LoanApplicationNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
            false,
            "LOAN_APPLICATION_NOT_FOUND",
            ex.getMessage(),
            new ArrayList<>()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RepaymentScheduleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRepaymentNotFound(RepaymentScheduleNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
            false,
            "REPAYMENT_SCHEDULE_NOT_FOUND",
            ex.getMessage(),
            new ArrayList<>()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateCustomerException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateCustomer(DuplicateCustomerException ex) {
        ErrorResponse response = new ErrorResponse(
            false,
            "DUPLICATE_CUSTOMER",
            ex.getMessage(),
            new ArrayList<>()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException ex) {
        List<FieldErrorResponse> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(new FieldErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        ErrorResponse response = new ErrorResponse(
            false,
            "VALIDATION_ERROR",
            "Invalid request",
            errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidLoanStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLoanStatus(InvalidLoanStatusException ex) {
        ErrorResponse response = new ErrorResponse(false, "INVALID_LOAN_STATUS", ex.getMessage(), new ArrayList<>());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        ErrorResponse response = new ErrorResponse(
            false,
            "INTERNAL_SERVER_ERROR",
            ex.getMessage(),
            new ArrayList<>()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}