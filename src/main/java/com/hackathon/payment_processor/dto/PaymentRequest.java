package com.hackathon.payment_processor.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class PaymentRequest {

    @NotNull(message = "Payment ID must be a valid UUID")
    private UUID paymentId;

    @NotBlank(message = "Debit account ID must not be blank")
    private String debitAccountId;

    @NotBlank(message = "Credit account ID must not be blank")
    private String creditAccountId;

    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;

    @Size(max = 35)
    private String reference;

    @NotNull
    @PastOrPresent
    private Instant timestamp;
}