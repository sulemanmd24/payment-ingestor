package com.hackathon.payment_processor.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationViolation {

    /*
     * Field Name
     * Example:
     * amount
     * currency
     * debitAccountId
     */
    private String field;

    /*
     * Validation Error Message
     * Example:
     * Amount must be greater than 0
     */
    private String message;
}