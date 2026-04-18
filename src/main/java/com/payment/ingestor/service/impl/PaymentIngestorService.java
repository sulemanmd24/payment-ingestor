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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

@Service
@Transactional
public class PaymentIngestorService implements com.payment.ingestor.service.PaymentIngestorService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentIngestorProducer paymentIngestorProducer;


    @Override
    @Transactional(readOnly = true)
    public Account getAccount(String accountId) {
        ModelMapper modelMapper = new ModelMapper();
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new PaymentIngestorException("Account not found", "ORD_404", 404));
        return modelMapper.map(accountEntity, Account.class);
    }

    @Override
    public String processPayment(final PaymentRequest paymentRequest) {
        if(paymentRequest.getDebitAccountId().equals(paymentRequest.getCreditAccountId())){
            throw new PaymentIngestorException("Debit and credit accounts cannot be the same", "ORD_400", 400);
        }
        var debitAccountOpt  = accountRepository.findById(paymentRequest.getDebitAccountId());
        var creditAccountOpt = accountRepository.findById(paymentRequest.getCreditAccountId());
        var debitAccountId  = debitAccountOpt.map(java.util.List::of).orElse(java.util.List.of());
        var creditAccountId = creditAccountOpt.map(java.util.List::of).orElse(java.util.List.of());
        if (CollectionUtils.isEmpty(debitAccountId)) {
            throw new PaymentIngestorException("Debit account not found", "ORD_404", 404);
        }
        if (CollectionUtils.isEmpty(creditAccountId)) {
            throw new PaymentIngestorException("Credit account not found", "ORD_404", 404);
        } else {
            AccountEntity existDebitAccountId = debitAccountId.get(0);
            AccountEntity existCreditAccountId = creditAccountId.get(0);
            if(!existDebitAccountId.getStatus().equals(AccountStatus.ACTIVE)
                    || !existCreditAccountId.getStatus().equals(AccountStatus.ACTIVE)){
                throw new PaymentIngestorException("Account is suspended", "ORD_422", 422);
            }else{
                paymentIngestorProducer.createPayment(paymentRequest);
            }
        }
        return paymentRequest.getPaymentId();
    }
}
