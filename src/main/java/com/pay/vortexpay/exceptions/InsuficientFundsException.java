package com.pay.vortexpay.exceptions;

public class InsuficientFundsException extends RuntimeException {
    public InsuficientFundsException(String message) {
        super(message);
    }
}
