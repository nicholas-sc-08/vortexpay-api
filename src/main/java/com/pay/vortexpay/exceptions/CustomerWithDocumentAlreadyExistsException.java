package com.pay.vortexpay.exceptions;

public class CustomerWithDocumentAlreadyExistsException extends RuntimeException {
    public CustomerWithDocumentAlreadyExistsException(String message) {
        super(message);
    }
}
