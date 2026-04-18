package com.payment.ingestor.service.impl;


import com.payment.ingestor.dto.Account;
import com.payment.ingestor.dto.PaymentRequest;
import com.payment.ingestor.entity.AccountEntity;
import com.payment.ingestor.exception.PaymentIngestorException;
import com.payment.ingestor.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentIngestorService implements com.payment.ingestor.service.PaymentIngestorService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account getAccount(String accountId) {
        ModelMapper modelMapper = new ModelMapper();
        Account account = null;
      Optional<AccountEntity> accountEntity =   accountRepository.findById(accountId);
        if (accountEntity.isEmpty()) {
            throw new PaymentIngestorException("Acount not  found", "ORD_404", 404);
        }else{
            AccountEntity existAccountEntity = accountEntity.get();
             account =  modelMapper.map(existAccountEntity, Account.class);
        }
        return account;
    }

    @Override
    public String processPayment(PaymentRequest request) {
        return "";
    }
}
