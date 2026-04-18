package com.payment.ingestor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.payment.ingestor.entity.AccountEntity;
import com.payment.ingestor.repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class PaymentIngestorApplication {
    private final AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(PaymentIngestorApplication.class, args);
	}

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        InputStream inputStream = getClass().getResourceAsStream("/data/accounts.json");
        if (inputStream != null) {
            List<AccountEntity> accounts = mapper.readValue(inputStream, new TypeReference<>() {});
            accountRepository.saveAll(accounts);
            System.out.println("✅ Accounts loaded: " + accounts.size() + " records saved to H2.");
        } else {
            System.out.println("⚠️ accounts.json file not found!");
        }
    }
}
