package com.hackathon.payment_processor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    /*
     * Error Time
     */
    private Instant timestamp;

    /*
     * HTTP Status
     * Example:
     * 400
     * 404
     * 409
     * 500
     */
    private int status;

    /*
     * Main Error Message
     */
    private String error;

    /*
     * API Path
     */
    private String path;

    /*
     * Validation Errors List
     */
    private List<ValidationViolation>
            violations;
}