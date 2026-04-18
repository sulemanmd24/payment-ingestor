package com.payment.ingestor.service;

import com.payment.ingestor.dto.Account;

public interface PaymentIngestorService {

    Account getAccount(String accountId);
}
