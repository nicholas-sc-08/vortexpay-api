package com.pay.vortexpay.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pay.vortexpay.dtos.request.TransactionDepositDTO;
import com.pay.vortexpay.dtos.response.TransactionResponseDTO;
import com.pay.vortexpay.entities.Account;
import com.pay.vortexpay.entities.Transaction;
import com.pay.vortexpay.exceptions.TransactionNotFoundException;
import com.pay.vortexpay.exceptions.AccountNotFoundException;
import com.pay.vortexpay.mappers.TransactionMapper;
import com.pay.vortexpay.repositories.AccountRepository;
import com.pay.vortexpay.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    public List<TransactionResponseDTO> findAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(transaction -> transactionMapper.toTransactionResponse(transaction)).collect(Collectors.toList());
    }

    public TransactionResponseDTO findTransactionById(UUID id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction with id "+id+" does not exists!"));
        return transactionMapper.toTransactionResponse(transaction);
    }

    @Transactional
    public TransactionResponseDTO transactionDepositAccount(TransactionDepositDTO dto) {
        Account sourceAccount = accountRepository.findById(dto.sourceAccount()).orElseThrow(() -> new AccountNotFoundException("Source account with id "+dto.sourceAccount()+" does not exists!"));
        Account destinationAccount = accountRepository.findById(dto.destinationAccount()).orElseThrow(() -> new AccountNotFoundException("Destination account with id "+dto.destinationAccount()+" does not exists!"));

        Transaction entity = transactionMapper.toTransactionEntity(dto, sourceAccount, destinationAccount);
        Transaction transaction = transactionRepository.save(entity);

        return transactionMapper.toTransactionResponse(transaction);
    }
}
