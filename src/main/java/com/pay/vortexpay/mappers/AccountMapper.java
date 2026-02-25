package com.pay.vortexpay.mappers;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.dtos.response.AccountResponseDTO;
import com.pay.vortexpay.entities.Account;

@Component
public class AccountMapper {
    public AccountResponseDTO toAccountResponse(Account account) {
        return new AccountResponseDTO(
            account.getId(),
            account.getAccountNumber(),
            account.getBalance(),
            account.getStatus(),
            account.getCustomer().getId(),
            account.getCustomer().getFullName()
        );
    }
}
