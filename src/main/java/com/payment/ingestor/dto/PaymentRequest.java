package com.payment.ingestor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {

    private String accountId;

    private String accountName;

    private String accountType;

    private String status;

    private String currency;

    private LocalDate openedDate;
}
