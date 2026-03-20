package com.pay.vortexpay.exceptions;

public class SecurityErrorException extends RuntimeException {
    public SecurityErrorException(String message) {
        super(message);
    }
}
