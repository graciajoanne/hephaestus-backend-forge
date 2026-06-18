package com.fif.exercisespring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateLoanApplicationRequest {
    @NotNull(message = "customer id is required")
    @JsonProperty("customer_id")
    private Long customerId;
    @NotNull(message = "loan amount is required")
    @JsonProperty("loan_amount")
    private Double loanAmount;
    @NotNull(message = "tenor month is required")
    @JsonProperty("tenor_month")
    private Integer tenorMonth;
    @NotBlank(message = "purpose is required")
    private String purpose;
}