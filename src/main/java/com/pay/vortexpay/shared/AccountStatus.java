package com.pay.vortexpay.shared;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE("active"), 
    BLOCKED("blocked");
    
    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }
}
