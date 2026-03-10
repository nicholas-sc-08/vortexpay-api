package com.pay.vortexpay.shared;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    TRANSFER("transfer"),
    PIX("pix");

    private final String transaction;
    
    TransactionType(String transaction) {
        this.transaction = transaction;
    }
}
