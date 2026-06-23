package com.fif.exercisespring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanApplication {

    private Long id;
    private Long customerId;
    private Double loanAmount;
    private Integer tenorMonth;
    private String purpose;
    private String status;
}