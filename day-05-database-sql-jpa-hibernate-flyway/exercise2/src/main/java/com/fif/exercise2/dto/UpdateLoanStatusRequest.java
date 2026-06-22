package com.fif.exercise2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateLoanStatusRequest {

    @NotBlank(message = "status is required")
    private String status;
}