package com.pay.vortexpay.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pay.vortexpay.dtos.response.AccountResponseDTO;
import com.pay.vortexpay.entities.Account;
import com.pay.vortexpay.exceptions.AccountNotFoundException;
import com.pay.vortexpay.mappers.AccountMapper;
import com.pay.vortexpay.repositories.AccountRepository;
import com.pay.vortexpay.repositories.CustomerRepository;
import com.pay.vortexpay.shared.AccountStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountResponseDTO> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> accountMapper.toAccountResponse(account)).collect(Collectors.toList());
    }

    public void deleteAccount(UUID customerId) {
        Account account = accountRepository.findByCustomerId(customerId).orElseThrow(() -> new AccountNotFoundException("Account with customer id "+customerId+ "does not exists!"));
        account.setStatus(AccountStatus.INACTIVE);

        accountRepository.save(account);
    }
}
