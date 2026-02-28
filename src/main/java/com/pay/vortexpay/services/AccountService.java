package com.pay.vortexpay.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pay.vortexpay.dtos.request.AccountCreateDTO;
import com.pay.vortexpay.dtos.request.AccountStatusDTO;
import com.pay.vortexpay.dtos.response.AccountResponseDTO;
import com.pay.vortexpay.entities.Account;
import com.pay.vortexpay.entities.Customer;
import com.pay.vortexpay.exceptions.AccountNotFoundException;
import com.pay.vortexpay.exceptions.CustomerAlreadyExistsException;
import com.pay.vortexpay.exceptions.CustomerNotFoundException;
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
    private final AccountNumberGenerator accountNumberGenerator;

    public List<AccountResponseDTO> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> accountMapper.toAccountResponse(account)).collect(Collectors.toList());
    }

    public AccountResponseDTO findAccountById(UUID id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account with id "+id+" does not exists!"));
        return accountMapper.toAccountResponse(account);
    }

    @Transactional
    public AccountResponseDTO createNewAccount(AccountCreateDTO dto) {
        accountRepository.findByCustomerId(dto.customerId()).ifPresent(a -> { throw new CustomerAlreadyExistsException("Account with customer id "+dto.customerId()+" already exists!"); });
        
        Customer customer = customerRepository.findById(dto.customerId()).orElseThrow(() -> new CustomerNotFoundException("Customer with id "+dto.customerId()+" does not exists!"));
        
        Account account = accountMapper.toAccountEntity(customer);
        account.setBalance(BigDecimal.ZERO);

        String newAccountNumber = accountNumberGenerator.generateUniqueAccountNumber();
        account.setAccountNumber(newAccountNumber);

        Account newAccount = accountRepository.save(account);
        return accountMapper.toAccountResponse(newAccount);
    }

    @Transactional
    public void changeAccountStatus(UUID id, AccountStatusDTO dto) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account with id "+id+" does not exists!"));
        account.setStatus(dto.status());

       accountRepository.save(account);
    }

    @Transactional
    public void deleteAccountByCustomerId(UUID customerId) {
        Account account = accountRepository.findByCustomerId(customerId).orElseThrow(() -> new AccountNotFoundException("Account with customer id "+customerId+ "does not exists!"));
        account.setStatus(AccountStatus.INACTIVE);

        account.setCustomer(null);
        accountRepository.save(account);

        customerRepository.deleteById(customerId);
    }
}
