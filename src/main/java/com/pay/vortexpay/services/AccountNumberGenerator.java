package com.pay.vortexpay.services;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.pay.vortexpay.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountNumberGenerator {
    private final AccountRepository accountRepository;
    private final SecureRandom random = new SecureRandom();

    public String generateUniqueAccountNumber() {
        String accountNumber;
        boolean alreadyExists;

        do {
            int rawNumber = random.nextInt(90000000) + 10000000;
            accountNumber = String.valueOf(rawNumber);

            alreadyExists = accountRepository.existsByAccountNumber(accountNumber); 
        } while(alreadyExists);
       
        return accountNumber;
    }
}
