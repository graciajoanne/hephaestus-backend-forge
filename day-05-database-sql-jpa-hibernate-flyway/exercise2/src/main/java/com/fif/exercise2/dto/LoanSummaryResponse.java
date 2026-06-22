package com.fif.exercise2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class LoanSummaryResponse {

    private String status;

    @JsonProperty("total_loan")
    private Long totalLoan;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;
}