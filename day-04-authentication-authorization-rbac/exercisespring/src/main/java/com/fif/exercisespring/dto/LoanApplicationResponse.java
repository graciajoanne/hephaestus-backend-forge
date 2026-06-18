package com.fif.exercisespring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanApplicationResponse {
    private Long id;
    @JsonProperty("customer_id")
    private Long customerId;
    @JsonProperty("loan_amount")
    private Double loanAmount;
    @JsonProperty("tenor_month")
    private Integer tenorMonth;
    private String purpose;
    private String status;
}