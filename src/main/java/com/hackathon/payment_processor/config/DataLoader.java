package com.hackathon.payment_processor.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hackathon.payment_processor.entity.Accounts;
import com.hackathon.payment_processor.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private  AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        InputStream inputStream = getClass().getResourceAsStream("/accounts.json");
        if (inputStream != null) {
            List<Accounts> accounts = mapper.readValue(inputStream, new TypeReference<>() {});
            accountRepository.saveAll(accounts);
            System.out.println("Accounts loaded: " + accounts.size() + " records saved to H2.");
        } else {
            System.out.println("accounts.json file not found!");
        }
    }
}
