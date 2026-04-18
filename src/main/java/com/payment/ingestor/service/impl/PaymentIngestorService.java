package com.payment.ingestor.service.impl;


import com.payment.ingestor.dto.Account;
import com.payment.ingestor.entity.AccountEntity;
import com.payment.ingestor.exception.PaymentIngestorException;
import com.payment.ingestor.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentIngestorService implements com.payment.ingestor.service.PaymentIngestorService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccount(String accountId) {
      Optional<AccountEntity> accountEntity =   accountRepository.findById(accountId);
        if (accountEntity.isEmpty()) {
            throw new PaymentIngestorException("Acount not  found", "ORD_404", 404);
        }else{
            AccountEntity existAccountEntity = accountEntity.get();
        }
        return null;
    }
}
