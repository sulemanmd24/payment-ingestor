package com.payment.ingestor.dto;

import com.payment.ingestor.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {

    private String paymentId;

    private String debitAccountId;

    private String creditAccountId;

    private Double amount;

    private Currency currency;

    private String reference;

    private LocalDate timestamp;
}
