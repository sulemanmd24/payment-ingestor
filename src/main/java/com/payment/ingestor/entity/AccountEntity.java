package com.payment.ingestor.entity;

import com.payment.ingestor.util.AccountStatus;
import com.payment.ingestor.util.AccountType;
import com.payment.ingestor.util.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    private String accountId;

    private String accountName;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDate openedDate;

}
