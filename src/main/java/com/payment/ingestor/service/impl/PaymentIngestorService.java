package com.payment.ingestor.service.impl;


import com.payment.ingestor.dto.Account;
import com.payment.ingestor.dto.PaymentRequest;
import com.payment.ingestor.entity.AccountEntity;
import com.payment.ingestor.exception.PaymentIngestorException;
import com.payment.ingestor.kafka.PaymentIngestorProducer;
import com.payment.ingestor.repository.AccountRepository;
import com.payment.ingestor.util.AccountStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentIngestorService implements com.payment.ingestor.service.PaymentIngestorService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentIngestorProducer paymentIngestorProducer;



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
    public String processPayment(final PaymentRequest paymentRequest) {
        if(paymentRequest.getDebitAccountId().equals(paymentRequest.getCreditAccountId())){
            throw new PaymentIngestorException("Account not  found", "ORD_404", 404);
        }
        Optional<AccountEntity> debitAccountId =   accountRepository.findById(paymentRequest.getDebitAccountId());
        Optional<AccountEntity> creditAccountId =   accountRepository.findById(paymentRequest.getCreditAccountId());
        if (debitAccountId.isEmpty()) {
            throw new PaymentIngestorException("Debit account can not be empty", "ORD_404", 404 );
        }
        if(creditAccountId.isEmpty()){
            throw new PaymentIngestorException("Credit account can not be empty", "ORD_404", 404 );
        }else{
            AccountEntity existDebitAccountId = debitAccountId.get();
            AccountEntity existCreditAccountId = creditAccountId.get();
            if(!existDebitAccountId.getStatus().equals(AccountStatus.ACTIVE)
                    || !existCreditAccountId.getStatus().equals(AccountStatus.ACTIVE) ){
                throw new PaymentIngestorException("Account is suspended", "ORD_422", 422);
            }else{
                paymentIngestorProducer.createPayment(paymentRequest);
            }
        }
        return paymentRequest.getPaymentId();
    }
}
