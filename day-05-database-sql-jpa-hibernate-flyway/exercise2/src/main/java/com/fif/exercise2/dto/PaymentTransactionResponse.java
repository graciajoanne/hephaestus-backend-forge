package com.fif.exercise2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class PaymentTransactionResponse {

    private Long id;

    @JsonProperty("repayment_schedule_id")
    private Long repaymentScheduleId;

    @JsonProperty("payment_reference")
    private String paymentReference;

    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    @JsonProperty("paid_at")
    private ZonedDateTime paidAt;

    private String status;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;
}