package com.pay.vortexpay.shared;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE("active"), 
    INACTIVE("inactive");
    
    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }
}
