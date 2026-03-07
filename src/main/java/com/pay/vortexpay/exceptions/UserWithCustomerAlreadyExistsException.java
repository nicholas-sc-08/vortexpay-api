package com.pay.vortexpay.exceptions;

public class UserWithCustomerAlreadyExistsException extends RuntimeException {
    public UserWithCustomerAlreadyExistsException(String message) {
        super(message);
    }
}
