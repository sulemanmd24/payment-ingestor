package com.payment.ingestor.controller;


import com.payment.ingestor.dto.Account;
import com.payment.ingestor.dto.PaymentRequest;
import com.payment.ingestor.service.PaymentIngestorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentIngestorController {

    @Autowired
    private PaymentIngestorService paymentIngestorService;

    @PostMapping("/payments")
    public ResponseEntity<Map<String, String>> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        final String paymentId = paymentIngestorService.processPayment(request);
        return ResponseEntity
                .accepted()
                .body(Map.of("paymentId", paymentId));
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId) {
        // Decode URL-encoded accountId (e.g., 20-15-88%2F43917265 -> 20-15-88/43917265)
        String decodedAccountId = URLDecoder.decode(accountId, StandardCharsets.UTF_8);
        final Account account = paymentIngestorService.getAccount(decodedAccountId);
        return ResponseEntity.ok(account);
    }

}
