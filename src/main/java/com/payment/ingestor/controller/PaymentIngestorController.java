package com.payment.ingestor.controller;


import com.payment.ingestor.dto.Account;
import com.payment.ingestor.dto.PaymentRequest;
import com.payment.ingestor.service.PaymentIngestorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentIngestorController {

    @Autowired
    private PaymentIngestorService paymentIngestorService;

    @PostMapping("/payments")
    public ResponseEntity<Map<String, String>> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        String paymentId = paymentIngestorService.processPayment(request);
        return ResponseEntity
                .accepted()
                .body(Map.of("paymentId", paymentId));
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId) {

      final Account account=  paymentIngestorService.getAccount(accountId);

        return ResponseEntity.ok(account);
    }

}
