package com.payment.ingestor.dto;

import com.payment.ingestor.util.AccountStatus;
import com.payment.ingestor.util.AccountType;
import com.payment.ingestor.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    private String accountId;

    private String accountName;

    private AccountType accountType;

    private AccountStatus status;

    private Currency currency;

    private LocalDate openedDate;

}
