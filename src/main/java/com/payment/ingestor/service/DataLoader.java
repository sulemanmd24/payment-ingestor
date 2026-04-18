package com.payment.ingestor.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.ingestor.dto.Account;
import com.payment.ingestor.entity.AccountEntity;
import com.payment.ingestor.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadAccounts(AccountRepository accountRepository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Account>> typeReference = new TypeReference<>() {};
            InputStream inputStream = getClass().getResourceAsStream("/data/accounts.json");
            if (inputStream != null) {
                // Change JSON mapping to AccountEntity
                List<AccountEntity> accounts = mapper.readValue(inputStream, new TypeReference<>() {});
                if (accountRepository.count() == 0) {
                    accountRepository.saveAll(accounts);
                }
                System.out.println("✅ Accounts data loaded successfully!");
            } else {
                System.out.println("⚠️ accounts.json file not found!");
            }
        };
    }
}
