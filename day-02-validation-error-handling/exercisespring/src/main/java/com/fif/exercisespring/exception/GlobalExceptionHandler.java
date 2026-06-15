package com.fif.exercisespring.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fif.exercisespring.dto.ErrorResponse;
import com.fif.exercisespring.dto.FieldErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException ex) {
        List<FieldErrorResponse> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(new FieldErrorResponse(convertToSnakeCase(error.getField()),error.getDefaultMessage())));
        ErrorResponse response = new ErrorResponse("VALIDATION_ERROR","Invalid request",errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> customerNotFound(CustomerNotFoundException ex) {
        ErrorResponse response = new ErrorResponse("CUSTOMER_NOT_FOUND", ex.getMessage(), Collections.emptyList());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalServerError(Exception ex) {
        ErrorResponse response = new ErrorResponse("INTERNAL_SERVER_ERROR","Unexpected error occurred", Collections.emptyList());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    private String convertToSnakeCase(String fieldName) {
    return fieldName.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
}
}