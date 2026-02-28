package com.pay.vortexpay.exceptions;

public class CustomerWithPhoneNumberAlreadyExistsException extends RuntimeException {
    public CustomerWithPhoneNumberAlreadyExistsException(String message) {
        super(message);
    }
}
