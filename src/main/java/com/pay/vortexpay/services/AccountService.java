package com.pay.vortexpay.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pay.vortexpay.dtos.response.AccountResponseDTO;
import com.pay.vortexpay.entities.Account;
import com.pay.vortexpay.mappers.AccountMapper;
import com.pay.vortexpay.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountResponseDTO> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> accountMapper.toAccountResponse(account)).collect(Collectors.toList());
    }
}
