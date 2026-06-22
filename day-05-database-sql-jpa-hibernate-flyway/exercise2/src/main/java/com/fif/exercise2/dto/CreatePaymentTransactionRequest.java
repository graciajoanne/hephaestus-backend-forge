package com.fif.exercise2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class CreatePaymentTransactionRequest {

    @NotNull(message = "repayment_schedule_id is required")
    @JsonProperty("repayment_schedule_id")
    private Long repaymentScheduleId;

    @NotBlank(message = "payment_reference is required")
    @JsonProperty("payment_reference")
    private String paymentReference;

    @NotNull(message = "paid_amount is required")
    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    @NotNull(message = "paid_at is required")
    @JsonProperty("paid_at")
    private ZonedDateTime paidAt;
}