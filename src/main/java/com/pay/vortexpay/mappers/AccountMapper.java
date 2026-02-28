package com.pay.vortexpay.mappers;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.dtos.response.AccountResponseDTO;
import com.pay.vortexpay.entities.Account;
import com.pay.vortexpay.entities.Customer;

@Component
public class AccountMapper {

    public Account toAccountEntity(Customer customer) {
        Account account = new Account();
        account.setCustomer(customer);
        return account;
    }

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
