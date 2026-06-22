package com.fif.exercise2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateLoanApplicationRequest {

    @NotNull(message = "customer_id is required")
    @JsonProperty("customer_id")
    private Long customerId;

    @NotNull(message = "loan_amount is required")
    @Min(value = 1, message = "loan_amount must be greater than 0")
    @JsonProperty("loan_amount")
    private BigDecimal loanAmount;

    @NotNull(message = "tenor_month is required")
    @Min(value = 1, message = "tenor_month must be greater than 0")
    @JsonProperty("tenor_month")
    private Integer tenorMonth;

    @NotBlank(message = "purpose is required")
    private String purpose;
}