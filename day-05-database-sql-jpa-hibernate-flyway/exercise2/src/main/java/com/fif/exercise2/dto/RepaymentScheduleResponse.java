package com.fif.exercise2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class RepaymentScheduleResponse {

    private Long id;

    @JsonProperty("installment_number")
    private Integer installmentNumber;

    @JsonProperty("due_date")
    private LocalDate dueDate;

    @JsonProperty("principal_amount")
    private BigDecimal principalAmount;

    @JsonProperty("interest_amount")
    private BigDecimal interestAmount;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    private String status;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}