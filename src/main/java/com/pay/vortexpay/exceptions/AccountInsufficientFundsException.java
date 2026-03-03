package com.pay.vortexpay.exceptions;

public class AccountInsufficientFundsException extends RuntimeException {
    public AccountInsufficientFundsException(String message) {
        super(message);
    }
}
