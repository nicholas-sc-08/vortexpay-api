package com.pay.vortexpay.mappers;

import java.util.UUID;

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
        UUID customerId = account.getCustomer() != null ? account.getCustomer().getId() : null;
        String customerName = account.getCustomer() != null ? account.getCustomer().getFullName() : null;
        
        return new AccountResponseDTO(
            account.getId(),
            account.getAccountNumber(),
            account.getBalance(),
            account.getStatus(),
            customerId,
            customerName
        );
    }
}
