package com.hackathon.payment_processor.exception;

import com.hackathon.payment_processor.dto.ErrorResponse;
import com.hackathon.payment_processor.dto.ValidationViolation;
import jakarta.servlet.http
        .HttpServletRequest;

import org.springframework.http
        .HttpStatus;

import org.springframework.http
        .ResponseEntity;

import org.springframework.web.bind
        .MethodArgumentNotValidException;

import org.springframework.web.bind.annotation
        .ExceptionHandler;

import org.springframework.web.bind.annotation
        .RestControllerAdvice;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Validation Errors
     */
    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ErrorResponse>
    handleValidationException(

            MethodArgumentNotValidException ex,

            HttpServletRequest request) {

        List<ValidationViolation>
                violations = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        new ValidationViolation(
                                error.getField(),
                                error
                                        .getDefaultMessage()
                        )
                )
                .toList();

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        400,
                        "Validation failed",
                        request.getRequestURI(),
                        violations
                );

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    /*
     * Account Not Found
     */
    @ExceptionHandler(
            AccountNotFoundException.class
    )
    public ResponseEntity<ErrorResponse>
    handleAccountNotFound(

            AccountNotFoundException ex,

            HttpServletRequest request) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        404,
                        ex.getMessage(),
                        request.getRequestURI(),
                        Collections.emptyList()
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    /*
     * Duplicate Payment
     */
    @ExceptionHandler(
            DuplicatePaymentException.class
    )
    public ResponseEntity<ErrorResponse>
    handleDuplicatePayment(

            DuplicatePaymentException ex,

            HttpServletRequest request) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        409,
                        ex.getMessage(),
                        request.getRequestURI(),
                        Collections.emptyList()
                );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    /*
     * Suspended Account
     */
    @ExceptionHandler(
            SuspendedAccountException.class
    )
    public ResponseEntity<ErrorResponse>
    handleSuspendedAccount(

            SuspendedAccountException ex,

            HttpServletRequest request) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        422,
                        ex.getMessage(),
                        request.getRequestURI(),
                        Collections.emptyList()
                );

        return ResponseEntity
                .status(422)
                .body(response);
    }

    /*
     * Invalid Currency
     */
    @ExceptionHandler(
            InvalidCurrencyException.class
    )
    public ResponseEntity<ErrorResponse>
    handleInvalidCurrency(

            InvalidCurrencyException ex,

            HttpServletRequest request) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        400,
                        ex.getMessage(),
                        request.getRequestURI(),
                        Collections.emptyList()
                );

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    /*
     * Unauthorized Access
     */
    @ExceptionHandler(
            UnauthorizedAccessException.class
    )
    public ResponseEntity<ErrorResponse>
    handleUnauthorized(

            UnauthorizedAccessException ex,

            HttpServletRequest request) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        401,
                        ex.getMessage(),
                        request.getRequestURI(),
                        Collections.emptyList()
                );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    /*
     * Kafka Publish Failure
     */
    @ExceptionHandler(
            KafkaPublishException.class
    )
    public ResponseEntity<ErrorResponse>
    handleKafkaFailure(

            KafkaPublishException ex,

            HttpServletRequest request) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        500,
                        ex.getMessage(),
                        request.getRequestURI(),
                        Collections.emptyList()
                );

        return ResponseEntity
                .internalServerError()
                .body(response);
    }

    /*
     * Generic Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGenericException(

            Exception ex,

            HttpServletRequest request) {

        ErrorResponse response =
                new ErrorResponse(
                        Instant.now(),
                        500,
                        "Internal server error",
                        request.getRequestURI(),
                        Collections.emptyList()
                );

        return ResponseEntity
                .internalServerError()
                .body(response);
    }
}
