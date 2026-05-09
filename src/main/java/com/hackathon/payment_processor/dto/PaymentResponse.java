package com.hackathon.payment_processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PaymentResponse {

    private UUID paymentId;

    private String message;
}
