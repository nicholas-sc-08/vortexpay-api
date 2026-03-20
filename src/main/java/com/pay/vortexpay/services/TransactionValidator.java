package com.pay.vortexpay.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.entities.Account;
import com.pay.vortexpay.exceptions.AccountBlockedException;
import com.pay.vortexpay.exceptions.AccountInactiveException;
import com.pay.vortexpay.exceptions.InsuficientFundsException;
import com.pay.vortexpay.repositories.AccountRepository;
import com.pay.vortexpay.shared.AccountStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionValidator {
    private final AccountRepository accountRepository;
    
    public void withdrawOfAccount(Account sourceAccount, BigDecimal amount) {
        if(sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsuficientFundsException("You don't have enough funds to withdraw of your account!");
        }

        if(sourceAccount.getStatus().equals(AccountStatus.BLOCKED)) {
            throw new AccountBlockedException("Cannot withdraw of a blocked account!");
        }

        if(sourceAccount.getStatus().equals(AccountStatus.INACTIVE)) {
            throw new AccountInactiveException("Cannot withdraw of a inactive account!");
        }

        BigDecimal newValue = sourceAccount.getBalance().subtract(amount);
        sourceAccount.setBalance(newValue);

        accountRepository.save(sourceAccount);
    }

    public void depositOnAccount(Account sourceAccount, Account destinationAccount, BigDecimal amount) {      
        withdrawOfAccount(sourceAccount, amount);

        if(destinationAccount.getStatus().equals(AccountStatus.BLOCKED)) {
            throw new AccountBlockedException("Cannot deposit money into a blocked account!");
        }

        if(destinationAccount.getStatus().equals(AccountStatus.INACTIVE)) {
            throw new AccountInactiveException("Cannot deposit money into a inactive account!");
        }

        BigDecimal newValue = destinationAccount.getBalance().add(amount);
        destinationAccount.setBalance(newValue);

        accountRepository.save(destinationAccount);
    }
}
