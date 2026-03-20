package com.pay.vortexpay.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pay.vortexpay.dtos.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(UserWithCustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleUserWithCustomerAlreadyExists(UserWithCustomerAlreadyExistsException ex) {
        return ResponseEntity.status(422).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleCustomerAlreadyExists(CustomerAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCustomerNotFound(CustomerNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(CustomerWithDocumentAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleDocumentAlreadyExists(CustomerWithDocumentAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(CustomerWithPhoneNumberAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleCustomerWithPhoneNumberAlreadyExists(CustomerWithPhoneNumberAlreadyExistsException ex) {
        return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleAccountNotFound(AccountNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(AccountInactiveException.class)
    public ResponseEntity<ErrorMessage> handleAccountInactive(AccountInactiveException ex) {
        return ResponseEntity.status(422).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(AccountBlockedException.class)
    public ResponseEntity<ErrorMessage> handleAccountBlocked(AccountBlockedException ex) {
        return ResponseEntity.status(422).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleTransactionNotFound(TransactionNotFoundException ex) {
        return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(InsuficientFundsException.class)
    public ResponseEntity<ErrorMessage> handInsuficientFunds(InsuficientFundsException ex) {
        return ResponseEntity.status(422).body(new ErrorMessage(ex.getMessage()));
    }
}
