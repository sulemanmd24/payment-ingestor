package com.payment.ingestor.service;

import com.payment.ingestor.dto.Account;
import com.payment.ingestor.dto.PaymentRequest;
import jakarta.validation.Valid;

public interface PaymentIngestorService {

    Account getAccount(String accountId);

    String processPayment( PaymentRequest request);
}
