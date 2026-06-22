package com.fif.exercise2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private boolean success;
    private String code;
    private String message;
    private List<FieldErrorResponse> errors;
}