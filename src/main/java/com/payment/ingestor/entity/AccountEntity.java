package com.payment.ingestor.entity;

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
    private String accountType;

    @Enumerated(EnumType.STRING)
    private String status;

    private String currency;


    private LocalDate openedDate;

}
